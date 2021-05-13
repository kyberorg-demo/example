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
    }

    @Test
    public void visualStateTest() {
        open("https://dev-example.kyberorg.io");
    }

    @Test
    public void http() {
        open("http://172.17.0.1");
    }

    @Test
    public void https() {
        open("https://172.17.0.1");
    }

}
