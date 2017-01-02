package com.erobic.springit.generator;

import com.erobic.springit.remote_models.UserRequest;
import com.erobic.springit.entities.TransactionDemoEntity;
import com.erobic.springit.entities.JacksonDemoEntity;
import com.erobic.springit.entities.User;

import java.time.LocalDateTime;

/**
 * Created by robik on 12/13/16.
 */
public class DataGenerator {
    public static TransactionDemoEntity generateDisease() {
        TransactionDemoEntity transactionDemoEntity = new TransactionDemoEntity();
        LocalDateTime localDateTime = LocalDateTime.now();
        transactionDemoEntity.setName("name " + localDateTime);
        transactionDemoEntity.setDescription("description " + localDateTime);
        return transactionDemoEntity;
    }

    public static JacksonDemoEntity generateJacksonExperiment() {
        JacksonDemoEntity obj = new JacksonDemoEntity();
        obj.setHidden("hidden field");
        obj.setCreatedOn(LocalDateTime.now());
        return obj;
    }

    public static User generateUser() {
        User user = new User();
        user.setUsername("robik");
        user.setPassword("p@ss11ee");
        return user;
    }

    public static UserRequest generateUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("robik");
        userRequest.setPassword("p@ss11ee");
        return userRequest;
    }
}
