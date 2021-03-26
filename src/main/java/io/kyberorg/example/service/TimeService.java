package io.kyberorg.example.service;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {
    public static final String DEFAULT_FORMAT = "dd/MM/yyyy HH:mm:ss z";

    public String currentTime() {
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        return dateTime.format(formatter);
    }
}
