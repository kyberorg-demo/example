package io.kyberorg.example;

/**
 * List of application endpoints.
 *
 * @since 1.0
 */
public class Endpoint {
    /**
     * REST API endpoints.
     */
    public static class Api {
        /**
         * API that reports application status.
         * <p>
         * /api/status
         */
        public static final String STATUS_API = "/api/status";

        /**
         * API that sends all received info back.
         * <p>
         * /api/echo
         */
        public static final String ECHO_API = "/api/echo";

        /**
         * API that reports current server time.
         * <p>
         * /api/time
         */
        public static final String TIME_API = "/api/time";
    }

    /**
     * User Interface Page.
     */
    public static class UI {
        /**
         * Web page, that opens by default.
         */
        public static final String HOME_PAGE = "";

        /**
         * Time Page.
         */
        public static final String TIME_PAGE = "time";

        /**
         * Echo Page.
         */
        public static final String ECHO_PAGE = "echo";

        /**
         * Status Page.
         */
        public static final String STATUS_PAGE = "status";

        /**
         * Unicom Page.
         */
        public static final String UNICOM_PAGE = "unicom";

        /**
         * Settings Page.
         */
        public static final String SETTINGS_PAGE = "settings";
        /**
         * Debug Page.
         */
        public static final String DEBUG_PAGE = "debug";


    }
}
