package com.erobic.springit.generator;

import com.erobic.springit.remote_models.UserRequest;
import com.erobic.springit.entities.Disease;
import com.erobic.springit.entities.JacksonExperiment;
import com.erobic.springit.entities.User;
import com.erobic.springit.utils.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Created by robik on 12/13/16.
 */
public class DataGenerator {
    public static Disease generateDisease() {
        Disease disease = new Disease();
        LocalDateTime localDateTime = LocalDateTime.now();
        disease.setName("name " + localDateTime);
        disease.setDescription("description " + localDateTime);
        return disease;
    }

    public static JacksonExperiment generateJacksonExperiment() {
        JacksonExperiment obj = new JacksonExperiment();
        obj.setHidden("hidden field");
        obj.setCreatedOn(LocalDateTime.now());
        return obj;
    }

    public static User generateUser() {
        User user = new User();
        user.setUsername("robik");
        user.setPassword("p@ss11ee");
        user.setRegisteredOn(DateTimeUtil.nowUTC());
        return user;
    }

    public static UserRequest generateUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("robik");
        userRequest.setPassword("p@ss11ee");
        return userRequest;
    }
}
