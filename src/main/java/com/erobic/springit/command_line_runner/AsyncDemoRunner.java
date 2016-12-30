package com.erobic.springit.command_line_runner;

import com.erobic.springit.remote_models.GithubUser;
import com.erobic.springit.services.AsyncDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by robik on 12/30/16.
 */
@Component
public class AsyncDemoRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AsyncDemoService asyncDemoService;

    @Autowired
    public AsyncDemoRunner(AsyncDemoService asyncDemoService) {
        this.asyncDemoService = asyncDemoService;
    }

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();

        Future<GithubUser> user1 = asyncDemoService.findUser("erobic");
        Future<GithubUser> user2 = asyncDemoService.findUser("Spring-Projects");
        Future<GithubUser> user3 = asyncDemoService.findUser("CloudFoundry");

        while (!(user1.isDone() && user2.isDone() && user3.isDone())) {
            Thread.sleep(30l);
        }

        long end = System.currentTimeMillis();
        logger.info("Time taken to run async tasks: {}", end - start);
        logger.info("user1: " + user1.get());
        logger.info("user2: " + user2.get());
        logger.info("user3: " + user3.get());
    }
}
