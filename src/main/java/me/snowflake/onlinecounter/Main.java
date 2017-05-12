package me.snowflake.onlinecounter;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ..
 */
public class Main {
    public static void main(String[] args) {
        JobDetail job = JobBuilder.newJob(StatusJob.class)
                .withIdentity("statusJob", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("statusTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?"))
                .build();

        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
