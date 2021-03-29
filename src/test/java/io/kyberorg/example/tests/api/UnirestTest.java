package io.kyberorg.example.tests.api;

import io.kyberorg.example.tests.util.TestUtils;

/**
 * API tests by doing requests using {@link kong.unirest.Unirest}.
 *
 * @since 1.0
 */
public abstract class UnirestTest {
    protected static final String TEST_URL = TestUtils.getTestUrl();
}
