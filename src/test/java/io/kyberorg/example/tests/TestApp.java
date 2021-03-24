package io.kyberorg.example.tests;

import io.kyberorg.example.tests.util.TestUtils;

/**
 * Test Application constants.
 *
 * @since 1.0
 */
public final class TestApp {
    private TestApp() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Test App Properties (those that start with -D...).
     */
    public static class Properties {
        /**
         * String with URL we are testing, should with protocol and port (i.e. http://localhost:8080)
         */
        public static final String TEST_URL = "test.url";

        /**
         * String with unique value, that identifies current test run. Used for naming videos.
         */
        public static final String BUILD_NAME = "test.buildName";

        /**
         * String with hostname of Selenium Grid.
         */
        public static final String GRID_HOSTNAME = "grid.hostname";



        /**
         * {@link Selenide} related constants.
         */
        public static class Selenide {
            /**
             * Selenium Grid location.
             */
            public static final String REMOTE = "selenide.remote";

            /**
             * String with browser name. Example: chrome, firefox etc.
             */
            public static final String BROWSER = "selenide.browser";
        }
    }

    /**
     * Default values.
     */
    public static class Defaults {
        /**
         * String with default build name (myHost-210203-1826).
         */
        public static final String BUILD_NAME = TestUtils.hostName() + "-" + TestUtils.timeStamp();

        /**
         * {@link Selenide}-related defaults.
         */
        public static class Selenide {
            /**
             * Chrome browser.
             */
            public static final String BROWSER = "chrome";
        }
    }
}
