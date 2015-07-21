package org.digital.dairy.scheduler.serviceimp;

import org.digital.dairy.config.QuartzConfig;
import org.digital.dairy.scheduler.service.QuartzSpecificJobTriggerService;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

/**
 * Created by Pradeep.P on 09-06-2015.
 */
@Service
public class QuartzSpecificJobTriggerServiceImp implements QuartzSpecificJobTriggerService{

    @Autowired
    SchedulerFactoryBean appSchedulerFactoryBean;

    @Override
    public void runSpecificJob(String JobName) {

        String groupName = QuartzConfig.JOB_GROUP;
        JobKey jobKey = new JobKey(QuartzConfig.JOB_PREFIX_NAME+JobName,groupName);
        try{
            appSchedulerFactoryBean.getScheduler().triggerJob(jobKey);
        }catch (SchedulerException e){
            System.out.println("Error while triggering job manually..");
        }

    }
}
