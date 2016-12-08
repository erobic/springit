package com.erobic.springit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by robik on 12/8/16.
 */
@RestController
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "This is where I experiment with Spring!";
    }
}
