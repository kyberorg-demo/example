package io.kyberorg.example.tests.ui.pages.status;

import io.kyberorg.example.tests.ui.SelenideTest;
import io.kyberorg.example.tests.ui.pageobjects.StatusPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.open;
import static io.kyberorg.example.tests.ui.pageobjects.Vaadin.waitForVaadin;

/**
 * Testing /(Status) URL.
 *
 * @since 1.0
 */
@SpringBootTest
public class StatusPageTest extends SelenideTest {

    /**
     * Test Setup.
     */
    @BeforeEach
    public void beforeTest() {
        tuneDriverWithCapabilities();
        open("/");
        waitForVaadin();
    }

    /**
     * On URL with slash (/) only opens status page.
     */
    @Test
    public void urlWithSlashWillOpenStatusPage() {
        StatusPage.PAGE_TITLE.should(exist);
    }
}
