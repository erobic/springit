package com.erobic.springit.exceptions;

/**
 * Created by robik on 12/23/16.
 */
public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException() {
    }

    public UsernameExistsException(String message) {
        super(message);
    }
}
