package io.kyberorg.example.api.time;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.service.TimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * API that reports current time.
 *
 * @since 1.0
 */
@RestController
public class TimeRestController {

    private final TimeService timeService;

    /**
     * Spring Constructor.
     *
     * @param timeService service, that reports time
     */
    public TimeRestController(final TimeService timeService) {
        this.timeService = timeService;
    }

    /**
     * GET /api/time Endpoint.
     *
     * @return string with time in {@link TimeService#DEFAULT_FORMAT}.
     */
    @GetMapping(Endpoint.Api.TIME_API)
    @ResponseBody
    public String reportTime() {
        return timeService.currentTime();
    }
}
