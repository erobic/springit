package com.erobic.springit.utils;

/**
 * Holds username for a particular thread
 * Created by robik on 1/1/17.
 */
public class RequestContextUtil {
    private static ThreadLocal<String> currentUsername = new ThreadLocal<>();

    private RequestContextUtil() {

    }

    public static void setUsername(String username) {
        currentUsername.set(username);
    }

    public static String getUsername() {
        return currentUsername.get();
    }

    public static void init() {
        setUsername(null);
    }
}
