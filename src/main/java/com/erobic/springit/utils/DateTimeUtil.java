package com.erobic.springit.utils;


import java.time.LocalDateTime;
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
    public static final OffsetDateTime offsetNowUTC() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }

    public static final LocalDateTime nowUTC() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }
}
