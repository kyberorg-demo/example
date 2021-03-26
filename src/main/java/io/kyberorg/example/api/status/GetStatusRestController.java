package io.kyberorg.example.api.status;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.dto.StatusDTO;
import io.kyberorg.example.service.StatusService;
import io.kyberorg.example.util.SystemStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStatusRestController {

    private final StatusService statusService;

    public GetStatusRestController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(path = Endpoint.Api.STATUS_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusDTO> getStatus() {
        SystemStatus applicationStatus = statusService.getApplicationStatus();
        SystemStatus databaseStatus = statusService.getDatabaseStatus();

        StatusDTO statusDTO = new StatusDTO(applicationStatus, databaseStatus);

        return ResponseEntity.ok(statusDTO);
    }
}
