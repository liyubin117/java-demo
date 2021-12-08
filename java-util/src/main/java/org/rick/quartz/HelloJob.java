package org.rick.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

//@DisallowConcurrentExecution表示此任务是有状态的
    public class HelloJob implements Job {
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDetail detail = context.getJobDetail();
            String name = detail.getJobDataMap().getString("name");
            System.out.println("say hello to " + name + " at " + new Date());
        }
    }