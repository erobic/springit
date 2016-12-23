package com.erobic.springit.utils;


import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Created by robik on 9/20/16.
 */
public class DateTimeUtil {
    /**
     * Returns current date time with "UTC" timezone
     *
     * @return
     */
    public static final OffsetDateTime nowUTC() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }
}
