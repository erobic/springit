package com.erobic.springit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by robik on 12/8/16.
 */
@RestController
public class HomeController {
    @Autowired
    ServletContext servletContext;

    @RequestMapping("/")
    public String index(){
        return "This is where I experiment with Spring!";
    }

    @RequestMapping("/servlet_context")
    public Map servletContext(){
        Map map = new HashMap();
        map.put("contextPath", servletContext.getContextPath());
        map.put("effectiveMajorVersion", Integer.toString(servletContext.getEffectiveMajorVersion()));
        map.put("effectiveMinorVersion", Integer.toString(servletContext.getEffectiveMinorVersion()));
        Set<String> filterNames = servletContext.getFilterRegistrations().keySet();
        map.put("filterNames", filterNames);
        return map;
    }
}
