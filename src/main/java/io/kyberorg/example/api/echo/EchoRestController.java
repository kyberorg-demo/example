package io.kyberorg.example.api.echo;

import io.kyberorg.example.Endpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Echo API.
 *
 * @since 1.0
 */
@RestController
public class EchoRestController {

    /**
     * API that implements Echo Protocol aka TCP/7 aka RFC862.
     *
     * @param body request body
     * @param request request object
     * @return same body as in request
     */
    @RequestMapping(value = Endpoint.Api.ECHO_API,
            consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
                    RequestMethod.OPTIONS}
    )
    @ResponseBody
    public String echoBack(@RequestBody(required = false) final String body,
                                                        final HttpServletRequest request) {
        return body;
    }
}
