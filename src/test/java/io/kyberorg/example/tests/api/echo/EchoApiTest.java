package io.kyberorg.example.tests.api.echo;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.tests.api.UnirestTest;
import kong.unirest.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static io.kyberorg.example.App.HttpCode.STATUS_200;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Echo API (GET /api/echo).
 *
 * @since 1.0
 */
public class EchoApiTest extends UnirestTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void echoReturnsCode200() {
        GetRequest request = Unirest.get(TEST_URL + Endpoint.Api.ECHO_API);
        HttpResponse result = request.asEmpty();
        assertEquals(STATUS_200, result.getStatus());
    }

    @Test
    public void echoWithBodyReturnsSameBody() {
        String stringBody = "StringBody";
        RequestBodyEntity request = Unirest.post(TEST_URL + Endpoint.Api.ECHO_API).body(stringBody);
        HttpResponse<String> result = request.asString();

        assertNotNull(result.getBody(), "Body is null");
        assertEquals(stringBody, result.getBody());
    }

    @Test
    public void echoWithoutBodyReturnsNothing() {
        HttpRequestWithBody request = Unirest.post(TEST_URL + Endpoint.Api.ECHO_API);
        HttpResponse<String> result = request.asString();

        assertTrue(StringUtils.isBlank(result.getBody()), "Body should be empty");
    }
}
