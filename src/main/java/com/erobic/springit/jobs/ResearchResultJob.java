package com.erobic.springit.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Demonstration of scheduling
 * Created by robik on 12/30/16.
 */
@Component
public class ResearchResultJob {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0,30 * * * * *")
    public void researchResultJob() {
        logger.info("Publishing new research results...");
    }
}
