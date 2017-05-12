package me.snowflake.onlinecounter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * ..
 */
public class StatusJob implements Job {
    private static final Logger LOG = getLogger(StatusJob.class);
    private final StatusProvider statusProvider;

    public StatusJob() {
        this.statusProvider = new StatusProvider(Config.getServers());
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String statuses = statusProvider.getStatuses();
        statusProvider.writeToFile(statuses);
        LOG.info("Ping");
    }
}
