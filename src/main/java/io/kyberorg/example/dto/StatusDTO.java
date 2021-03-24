package io.kyberorg.example.dto;

import io.kyberorg.example.util.SystemStatus;
import lombok.Data;

@Data
public class StatusDTO {
    private final SystemStatus application;
    private final SystemStatus database;
}
