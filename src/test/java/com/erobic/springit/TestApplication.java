package com.erobic.springit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by robik on 12/8/16.
 */
@SpringBootApplication(exclude = Application.class)
@EnableTransactionManagement
@EnableCaching
public class TestApplication {
    private static Logger logger = LoggerFactory.getLogger(TestApplication.class);
    public static void main(String[] args) {
        logger.info("Creating test application...");
        SpringApplication.run(TestApplication.class, args);
    }
}
