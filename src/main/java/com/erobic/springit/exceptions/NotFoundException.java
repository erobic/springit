package com.erobic.springit.exceptions;

/**
 * Created by robik on 12/16/16.
 */
public class NotFoundException extends CustomException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
