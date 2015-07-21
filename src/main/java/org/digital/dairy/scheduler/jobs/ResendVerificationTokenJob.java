package org.digital.dairy.scheduler.jobs;

import org.digital.dairy.scheduler.service.ResendVerificationTokenService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pradeep.P on 06-06-2015.
 */

public class ResendVerificationTokenJob implements Job{

    @Autowired
    ResendVerificationTokenService resendVerificationTokenService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("executeJobs trigger1");
        resendVerificationTokenService.resendVerificationToken();
    }
}
