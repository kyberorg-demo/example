package io.kyberorg.example.tests.api.status;

import io.kyberorg.example.Endpoint;
import io.kyberorg.example.tests.api.UnirestTest;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import static io.kyberorg.example.App.HttpCode.STATUS_200;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testing Status API (GET /api/status).
 *
 * @since 1.0
 */
public class GetStatusApiTest extends UnirestTest {

    /**
     * Tests that Status API returns http code 200.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void getStatusApiReturnsCode200() {
        GetRequest request = Unirest.get(TEST_URL + Endpoint.Api.STATUS_API);
        HttpResponse result = request.asEmpty();
        assertEquals(STATUS_200, result.getStatus());
    }

    /**
     * Tests that Status API returns JSON with Application and Database statuses.
     */
    @Test
    public void getStatusApiReturnsJsonWithStatuses() {
        GetRequest request = Unirest.get(TEST_URL + Endpoint.Api.STATUS_API);
        HttpResponse<JsonNode> response = request.asJson();

        JsonNode responseJson = response.getBody();
        assertNotNull(responseJson.getObject().get("application"), "Reply JSON has no 'application' field");
        assertNotNull(responseJson.getObject().get("database"), "Reply JSON has no 'database' field");
    }
}
