package io.kyberorg.example.tests.ui.pages;

import io.kyberorg.example.tests.ui.SelenideTest;
import io.kyberorg.example.tests.ui.pageobjects.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.open;
import static io.kyberorg.example.tests.ui.pageobjects.Vaadin.waitForVaadin;

/**
 * Testing /(Home) URL.
 *
 * @since 1.0
 */
@SpringBootTest
public class HomePageTest extends SelenideTest {

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
     * On URL with slash (/) only opens home page with 3 items.
     */
    @Test
    public void urlWithSlashWillOpenHomePage() {
        HomePage.PAGE_TITLE.should(exist);
        HomePage.SITE_PAGE_ITEMS.shouldHaveSize(3);
    }
}
