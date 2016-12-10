package com.erobic.springit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String index() {
        return "This is where I experiment with Spring!";
    }

    @RequestMapping("/autowired/servlet_context")
    public Map servletContextByAutowired() {
        return fromServletContext(servletContext);
    }

    @RequestMapping("/servlet_request/servlet_context")
    public Map servletContextByServletRequest(HttpServletRequest request) {
        return fromServletContext(request.getServletContext());
    }

    @RequestMapping("/response_status/not_found")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void notFoundByResponseStatus() {
    }

    @RequestMapping("/servlet_response/not_found")
    public void notFoundByServletResponse(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
    }

    private Map fromServletContext(ServletContext servletContext) {
        Map map = new HashMap();
        map.put("contextPath", servletContext.getContextPath());
        map.put("effectiveMajorVersion", Integer.toString(servletContext.getEffectiveMajorVersion()));
        map.put("effectiveMinorVersion", Integer.toString(servletContext.getEffectiveMinorVersion()));
        Set<String> filterNames = servletContext.getFilterRegistrations().keySet();
        map.put("filterNames", filterNames);
        return map;
    }
}
