package io.kyberorg.example;

/**
 * Application constants.
 *
 * @since 1.0
 */
public final class App {

    /**
     * List of used HTTP response code. Used to prevent magic number issue.
     *
     * @since 1.0
     */
    public static final class HttpCode {
        /**
         * 200 - ok.
         */
        public static final int STATUS_200 = 200;
    }

    /**
     * Application property constants.
     */
    public static class Properties {
        public static final String APP_SITE_TITLE = "app.site-title";
    }

    /**
     * Application constants.
     */
    public static class C {
        public static final int INVALID_ID = -2;
    }
}
