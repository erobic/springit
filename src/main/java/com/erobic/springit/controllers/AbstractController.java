package com.erobic.springit.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by robik on 12/12/16.
 */
public abstract class AbstractController {
    protected ResponseEntity created(Long id) {
        Map<String, Long> createdResponse = new HashMap();
        createdResponse.put("id", id);
        return new ResponseEntity(createdResponse, HttpStatus.CREATED);
    }
}
