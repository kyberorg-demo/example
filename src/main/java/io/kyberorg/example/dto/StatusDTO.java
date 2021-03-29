package io.kyberorg.example.dto;

import io.kyberorg.example.api.status.GetStatusRestController;
import io.kyberorg.example.util.SystemStatus;
import lombok.Data;

/**
 * JSON that {@link GetStatusRestController} sends in reply.
 *
 * @since 1.0
 */
@Data
public class StatusDTO {
    private final SystemStatus application;
    private final SystemStatus database;
}
