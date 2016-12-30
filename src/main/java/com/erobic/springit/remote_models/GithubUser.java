package com.erobic.springit.remote_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by robik on 12/30/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {
    private String name;
    private String blog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
