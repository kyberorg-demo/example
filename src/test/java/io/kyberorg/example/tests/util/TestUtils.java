package io.kyberorg.example.tests.util;

import io.kyberorg.example.tests.TestApp;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Useful stuff for testing.
 *
 * @since 1.0
 */
public class TestUtils {
    private TestUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Hostname of executing machine.
     *
     * @return string with hostname
     */
    public static String hostName() {
        return HostIdentifier.getHostName();
    }

    /**
     * Provides current time for test naming.
     *
     * @return string with timestamp
     */
    public static String timeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd-HHmm");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    /**
     * Provides URL we should test.
     *
     * @return string that has URL to run tests against.
     */
    public static String getTestUrl() {
        return System.getProperty(TestApp.Properties.TEST_URL, "http://localhost:8080");
    }
}
