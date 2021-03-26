package io.kyberorg.example.api.echo;

import io.kyberorg.example.Endpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EchoRestController {

    @RequestMapping(value = Endpoint.Api.ECHO_API,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
                    RequestMethod.OPTIONS}
    )
    @ResponseBody
    public String echoBack(@RequestBody(required = false) String body,
                                                        HttpServletRequest request) {
        return body;
    }
}
