package io.kyberorg.example.tests.ui.pages;

import io.kyberorg.example.tests.ui.SelenideTest;
import io.kyberorg.example.tests.ui.pageobjects.EchoPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;
import static io.kyberorg.example.tests.ui.pageobjects.Vaadin.waitForVaadin;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing /echo page.
 */
@SpringBootTest
public class EchoPageTest extends SelenideTest {

    /**
     * Test Setup.
     */
    @BeforeEach
    public void beforeTest() {
        tuneDriverWithCapabilities();
        open("/echo");
        waitForVaadin();
    }

    /**
     * Testing initial visual state.
     */
    @Test
    public void visualStateTest() {
        EchoPage.PAGE_TITLE.shouldBe(visible);
        EchoPage.ABOUT_SECTION.shouldBe(visible);
        EchoPage.INPUT.shouldBe(visible);
        EchoPage.SEND_BUTTON.shouldBe(visible);
        EchoPage.SEND_BUTTON.shouldBe(enabled);
        EchoPage.RESULT_SPAN.shouldBe(empty);
    }

    /**
     * On send with valid input - result with same value should appear at result span.
     */
    @Test
    public void onInputAndSendResultShouldAppear() {
        String inputValue = "WeSendYouOurLove";
        EchoPage.INPUT.setValue(inputValue);
        EchoPage.SEND_BUTTON.click();

        EchoPage.RESULT_SPAN.shouldNotBe(empty);
        String result = EchoPage.RESULT_SPAN.getText();
        assertEquals(result, inputValue);
    }

    /**
     * On successful send - input should be cleared.
     */
    @Test
    public void onValidSendInputShouldBeCleared() {
        String inputValue = "WeSendYouOurLove";
        EchoPage.INPUT.setValue(inputValue);
        EchoPage.SEND_BUTTON.click();

        EchoPage.INPUT.shouldBe(empty);
    }

}
