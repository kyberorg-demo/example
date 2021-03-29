package io.kyberorg.example.service;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service, that reports current time.
 *
 * @since 1.0
 */
@Service
public class TimeService {
    /**
     * String with default time format.
     */
    public static final String DEFAULT_FORMAT = "dd/MM/yyyy HH:mm:ss z";

    /**
     * Reports current time in server's default time zone.
     *
     * @return string with current time in {@link #DEFAULT_FORMAT}
     */
    public String currentTime() {
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        return dateTime.format(formatter);
    }
}
