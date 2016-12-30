package com.erobic.springit.controllers;

import com.erobic.springit.remote_models.CreatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by robik on 12/12/16.
 */
public abstract class AbstractController<T> {
    protected ResponseEntity created(Long id) {
        CreatedResponse createdResponse = new CreatedResponse();
        createdResponse.setId(id);
        return new ResponseEntity(createdResponse, HttpStatus.CREATED);
    }

    protected ResponseEntity<T> get(T body) {
        if (body == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(body, HttpStatus.OK);
        }
    }
}
