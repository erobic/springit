package com.erobic.springit.web.controllers;

import com.erobic.springit.services.InitializingBeanDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @RequestMapping("/secure_home")
    public String secureHome() { return "secure_home"; }

    @RequestMapping("/servlet_context/using_Autowired")
    public Map servletContextByAutowired() {
        return fromServletContext(servletContext);
    }

    @RequestMapping("/servlet_context/using_HttpServletRequest")
    public Map servletContextByServletRequest(HttpServletRequest request) {
        return fromServletContext(request.getServletContext());
    }

    @RequestMapping("/not_found/ResponseStatus")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void notFoundByResponseStatus() {
    }

    @RequestMapping("/not_found/using_HttpServletResponse")
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

    @RequestMapping(value = "/images/tree/using_produces",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] treeUsingProduces() throws IOException, URISyntaxException {
        URL url = servletContext.getResource("/resources/images/tree.jpg");
        Path path = Paths.get(url.toURI());
        return java.nio.file.Files.readAllBytes(path);
    }

    @RequestMapping(value = "/images/tree/using_ResponseEntity")
    public ResponseEntity<byte[]> treeUsingResponseEntity() throws IOException, URISyntaxException {
        byte[] bytes = Files.readAllBytes(
                Paths.get(
                        servletContext.getResource("/resources/images/tree.jpg").toURI()
                ));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
        return responseEntity;
    }

    @Autowired
    InitializingBeanDemo initializingBeanDemo;

    @RequestMapping(value = "/initializing_bean")
    public Boolean initializingBean() {
        return initializingBeanDemo.getInitialized();
    }
}
