package com.erobic.springit.exceptions;

/**
 * Created by robik on 12/16/16.
 */
public class CustomException extends RuntimeException {

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
