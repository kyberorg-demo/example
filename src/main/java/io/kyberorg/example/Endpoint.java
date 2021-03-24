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
    }
    
    public static class UI {
        public static final String HOME_PAGE = "";
        public static final String TIME_PAGE = "time";
        public static final String ECHO_PAGE = "echo";
        public static final String STATUS_PAGE = "status";
    }
}
