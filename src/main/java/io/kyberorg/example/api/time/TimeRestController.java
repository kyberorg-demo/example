package io.kyberorg.example.api.time;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.service.TimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeRestController {

    private final TimeService timeService;

    public TimeRestController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping(Endpoint.Api.TIME_API)
    @ResponseBody
    public String reportTime() {
        return timeService.currentTime();
    }
}
