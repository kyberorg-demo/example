package io.kyberorg.example.tests.ui;

import com.codeborne.selenide.Configuration;
import io.kyberorg.example.tests.TestApp;
import io.kyberorg.example.tests.util.TestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.MutableCapabilities;

/**
 * Base for all UI Tests, which run with Selenide.
 *
 * @since 1.0
 */
public abstract class SelenideTest {

    protected static final String BASE_URL = TestUtils.getTestUrl();

    private static final String BUILD_NAME =
            System.getProperty(TestApp.Properties.BUILD_NAME, TestApp.Defaults.BUILD_NAME);

    private static final String SELENIDE_BROWSER =
            System.getProperty(TestApp.Properties.Selenide.BROWSER, TestApp.Defaults.Selenide.BROWSER);

    private static final int SELENIDE_TIMEOUT = 4000;

    /**
     * Common Runner Setup and Info.
     */
    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = BASE_URL;
        Configuration.reportsFolder = "target/reports";
        Configuration.timeout = SELENIDE_TIMEOUT;
        Configuration.browser = SELENIDE_BROWSER;
        //critical for Vaadin input
        Configuration.fastSetValue = true;

        if (shouldRunTestsAtGrid()) {
            Configuration.remote = getGridFullUrl();
        }
    }

    /**
     * Setting capabilities for driver.
     * Needs to run before {@link com.codeborne.selenide.Selenide#open()} method.
     */
    protected void tuneDriverWithCapabilities() {
        if (shouldRunTestsAtGrid()) {
            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("enableVnc",  true);
            capabilities.setCapability("screenResolution", "1920x1080x24");

            capabilities.setCapability("name",  BUILD_NAME);

            capabilities.setCapability("enableVideo",  true);
            capabilities.setCapability("videoName",  BUILD_NAME + ".mp4");

            capabilities.setCapability("enableLog",  true);
            capabilities.setCapability("logName",  BUILD_NAME + ".log");

            Configuration.browserCapabilities.merge(capabilities);
        }
    }

    private static boolean shouldRunTestsAtGrid() {
        String selenideRemote = System.getProperty(TestApp.Properties.Selenide.REMOTE, "");
        String gridHostname = System.getProperty(TestApp.Properties.GRID_HOSTNAME, "");
        return StringUtils.isNotBlank(selenideRemote) || StringUtils.isNotBlank(gridHostname);
    }

    private static String getGridFullUrl() {
        final String httpsPrefix = "https://";
        final String httpPrefix = "http://";
        final String gridPostfix = "/wd/hub";

        String selenideRemote = System.getProperty(TestApp.Properties.Selenide.REMOTE, "");
        if (StringUtils.isNotBlank(selenideRemote)) {
            return selenideRemote;
        }

        String gridHostname = System.getProperty(TestApp.Properties.GRID_HOSTNAME);
        boolean hostnameStringHasProtocol = gridHostname.startsWith(httpsPrefix) || gridHostname.startsWith(httpPrefix);
        boolean hostnameStringHasGridPostfix = gridHostname.contains(gridPostfix);
        if (hostnameStringHasProtocol && hostnameStringHasGridPostfix) {
            return gridHostname;
        } else if (hostnameStringHasProtocol) {
            return gridHostname + gridPostfix;
        } else if (hostnameStringHasGridPostfix) {
            return httpPrefix + gridHostname;
        } else {
            return httpPrefix + gridHostname + gridPostfix;
        }
    }
}
