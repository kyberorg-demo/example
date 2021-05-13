package io.kyberorg.example.tests.ui.pages;

import io.kyberorg.example.tests.ui.SelenideTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;

/**
 * Testing /echo page.
 */
@SpringBootTest
public class DebugTest extends SelenideTest {

    /**
     * Test Setup.
     */
    @BeforeEach
    public void beforeTest() {
        tuneDriverWithCapabilities();
        open("https://google.com");
    }

    /**
     * Testing initial visual state.
     */
    @Test
    public void visualStateTest() {
        open("http://172.0.0.1");
        open("https://172.0.0.1");
        open("https://dev-example.kyberorg.io");
    }

}
