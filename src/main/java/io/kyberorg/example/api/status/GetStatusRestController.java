package io.kyberorg.example.api.status;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.dto.StatusDTO;
import io.kyberorg.example.service.StatusService;
import io.kyberorg.example.util.SystemStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API that report application state.
 *
 * @since 1.0
 */
@RestController
public class GetStatusRestController {

    private final StatusService statusService;

    /**
     * Spring Constructor.
     *
     * @param statusService autowired service that provide component's state.
     *      *
     */
    public GetStatusRestController(final StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * GET /api/status Endpoint.
     *
     * @return JSON with status.
     */
    @GetMapping(path = Endpoint.Api.STATUS_API, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatusDTO> getStatus() {
        SystemStatus applicationStatus = statusService.getApplicationStatus();
        SystemStatus databaseStatus = statusService.getDatabaseStatus();

        StatusDTO statusDTO = new StatusDTO(applicationStatus, databaseStatus);

        return ResponseEntity.ok(statusDTO);
    }
}
