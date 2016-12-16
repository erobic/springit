package com.erobic.springit.controllers;

import com.erobic.springit.exceptions.CustomException;
import com.erobic.springit.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robik on 12/16/16.
 */
@RestController
@RequestMapping("/exceptions")
public class ExceptionDemoController {

    @RequestMapping(value = "/not_found", method = RequestMethod.GET)
    public void notFound() {
        throw new NotFoundException();
    }

    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public void custom() {
        throw new CustomException();
    }
}
