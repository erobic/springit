package com.erobic.springit.generator;

import com.erobic.springit.models.Disease;

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
}
