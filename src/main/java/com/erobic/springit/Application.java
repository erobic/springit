package com.erobic.springit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by robik on 12/8/16.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
