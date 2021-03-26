package io.kyberorg.example.tests.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.kyberorg.example.tests.ui.SelenideTest;
import io.kyberorg.example.tests.ui.pageobjects.StatusPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Condition.*;
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
        open("/status");
        waitForVaadin();
    }

    /**
     * Testing initial visual state
     */
    @Test
    public void visualStateTest() {
        StatusPage.PAGE_TITLE.should(exist);
        StatusPage.APP_STATUS_LABEL.shouldBe(visible).shouldNotBe(empty);
        StatusPage.APP_STATUS_TEXT.shouldBe(visible).shouldNotBe(empty);
        StatusPage.DB_STATUS_LABEL.shouldBe(visible).shouldNotBe(empty);
        StatusPage.DB_STATUS_TEXT.shouldBe(visible).shouldNotBe(empty);
        StatusPage.RANDOM_COMPONENT_STATUS_LABEL.shouldBe(visible).shouldNotBe(empty);
        StatusPage.RANDOM_COMPONENT_STATUS_TEXT.shouldBe(visible).shouldNotBe(empty);
    }

    /**
     * Testing that string have correct words inside.
     */
    @Test
    public void wordingTest() {
        StatusPage.PAGE_TITLE.shouldHave(text("System Status"));
        StatusPage.APP_STATUS_LABEL.shouldHave(text("Application Status"));
        StatusPage.DB_STATUS_LABEL.shouldHave(text("Database Status"));
        StatusPage.RANDOM_COMPONENT_STATUS_LABEL.shouldHave(text("Random")).shouldHave(text("Status"));
    }

    /**
     * Testing that conditional formatting works.
     */
    @Test
    public void conditionalFormattingTest() {
        testStatus(StatusPage.APP_STATUS_TEXT);
        testStatus(StatusPage.DB_STATUS_TEXT);
        testStatus(StatusPage.RANDOM_COMPONENT_STATUS_TEXT);
    }

    private void testStatus(SelenideElement statusTextElement) {
        switch (statusTextElement.getText()) {
            case "UP":
                statusTextElement.shouldHave(cssClass("green"));
                break;
            case "DOWN":
                statusTextElement.shouldHave(cssClass("red"));
                break;
            default:
                Assertions.fail("Status text is not UP nor DOWN");

        }
    }
}
