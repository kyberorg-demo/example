package io.kyberorg.example.tests.api.time;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.tests.api.UnirestTest;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.kyberorg.example.App.HttpCode.STATUS_200;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Time API (/api/time).
 */
class TimeApiTest extends UnirestTest {

    public static final String DEFAULT_FORMAT = "dd/MM/yyyy HH:mm:ss z";

    /**
     * Testing that for default call (without params) - response code is 200.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void timeApiReturnsCode200() {
        GetRequest request = Unirest.get(TEST_URL + Endpoint.Api.TIME_API);
        HttpResponse result = request.asEmpty();
        assertEquals(STATUS_200, result.getStatus());
    }

    /**
     * Testing that for default request - response body if not empty.
     */
    @Test
    public void timeApiReturnsNonEmptyResult() {
        GetRequest request = Unirest.get(TEST_URL + Endpoint.Api.TIME_API);
        HttpResponse<String> result = request.asString();
        assertTrue(StringUtils.isNotBlank(result.getBody()), "Time API replies with empty body");
    }

    /**
     * Testing that time API return string with valid time.
     */
    @Test
    public void timeApiReturnValidTime() {
        GetRequest request = Unirest.get(TEST_URL + Endpoint.Api.TIME_API);
        HttpResponse<String> result = request.asString();
        String body = result.getBody();

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        try {
            Date date = sdf.parse(body);
            assertNotNull(date);
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }


}
