package com.erobic.springit.services;

import com.erobic.springit.remote_models.GithubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by robik on 12/30/16.
 */
@Service
public class AsyncDemoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RestTemplate restTemplate;

    @Autowired
    public AsyncDemoService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public Future<GithubUser> findUser(String user) throws InterruptedException {
        String url = String.format("https://api.github.com/users/%s", user);
        GithubUser githubUser = restTemplate.getForObject(url, GithubUser.class);
        Thread.sleep(1000l);
        return new AsyncResult<>(githubUser);
    }
}
