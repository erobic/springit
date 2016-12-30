package com.erobic.springit.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Demonstration of scheduling
 * Created by robik on 12/30/16.
 */
@Component
@Profile("job")
public class SchedulerDemoJob {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "${schedulerDemoJob.cron}")
    public void cronJob() {
        logger.info("Publishing new research results...");
    }

    @Scheduled(fixedDelayString = "${schedulerDemoJob.fixedDelayString}")
    public void fixedDelayJob() throws InterruptedException {
        logger.info("Delayed by 10 seconds, sleeping for 20 seconds");
        Thread.sleep(20000l);
    }
}
