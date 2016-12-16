package com.erobic.springit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by robik on 12/16/16.
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private ErrorResponse handleException(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    private ErrorResponse handleException(Exception ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
