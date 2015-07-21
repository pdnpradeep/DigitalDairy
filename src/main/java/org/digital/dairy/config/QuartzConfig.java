package org.digital.dairy.config;

import org.digital.dairy.scheduler.JobBeanConfig.AutowiringSpringBeanJobFactory;
import org.digital.dairy.scheduler.jobs.ResendVerificationTokenJob;
import org.digital.dairy.utils.DBCPConnectionDataSource;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Pradeep.P on 30-05-2015.
 */
@Configuration
public class QuartzConfig {

    private static final String SCHEDULER_NAME = "SW_QUARTZ_SCHEDULER";
    public static final String JOB_GROUP = "SPRING3-QUARTZ";
    public static final String JOB_PREFIX_NAME = "createJobDetailFactoryBeanFor";

    @Autowired
    private DBCPConnectionDataSource dataSource;

    @Autowired
    private JpaTransactionManager transactionManager;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${cron.ResendVerificationTokenJob.time}")
    private String cronExpResendVerificationTokenJob;

    public @Bean
    JobDetailFactoryBean createJobDetailFactoryBeanForResendVerificationTokenJob(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(ResendVerificationTokenJob.class);
        jobDetailFactoryBean.setGroup(JOB_GROUP);
        jobDetailFactoryBean.setDurability(true);
        return jobDetailFactoryBean;
    }

    public @Bean
    CronTriggerFactoryBean createCronTriggerFactoryBeanForResendVerificationTokenJob(){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(createJobDetailFactoryBeanForResendVerificationTokenJob().getObject());
        cronTriggerFactoryBean.setCronExpression(cronExpResendVerificationTokenJob);
        cronTriggerFactoryBean.setGroup(JOB_GROUP);
        return cronTriggerFactoryBean;
    }

    public @Bean(name="appSchedulerFactoryBean")
    SchedulerFactoryBean createSchedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);

        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setTransactionManager(transactionManager);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setSchedulerName(SCHEDULER_NAME);
        schedulerFactoryBean.setQuartzProperties(getProperties());

        Trigger[] triggers =
                {
                        createCronTriggerFactoryBeanForResendVerificationTokenJob().getObject(),
                };
        schedulerFactoryBean.setTriggers(triggers);

        return schedulerFactoryBean;
    }

    private Properties getProperties(){
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
        }
        catch (IOException ex) {
          //  logger.warn("Cannot load quartz.properties.");
//			ex.printStackTrace();
        }
        return prop;
    }

}
