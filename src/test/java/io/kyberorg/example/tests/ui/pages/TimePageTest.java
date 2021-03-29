package io.kyberorg.example.tests.ui.pages;

import io.kyberorg.example.service.TimeService;
import io.kyberorg.example.tests.ui.SelenideTest;
import io.kyberorg.example.tests.ui.pageobjects.TimePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.kyberorg.example.tests.ui.pageobjects.Vaadin.waitForVaadin;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Testing Time Page.
 *
 * @since 1.0
 */
@SpringBootTest
public class TimePageTest extends SelenideTest {

    /**
     * Test Setup.
     */
    @BeforeEach
    public void beforeTest() {
        tuneDriverWithCapabilities();
        open("/time");
        waitForVaadin();
    }

    /**
     *
     */
    @Test
    public void visualStateTest() {
        TimePage.PAGE_TITLE.shouldBe(visible);
        TimePage.ABOUT_SPAN.shouldBe(visible).shouldNotBe(empty);
        TimePage.TIME_LABEL.shouldBe(visible).shouldNotBe(empty);
        TimePage.TIME_SPAN.shouldBe(visible).shouldNotBe(empty);
    }

    /**
     * Tests that {@link TimePage#TIME_SPAN} has string that is valid time string.
     */
    @Test
    public void timeSpanHasValidTime() {
        String timeString = TimePage.TIME_SPAN.getText();
        Assertions.assertTrue(StringUtils.isNotBlank(timeString), "Time string is empty");

        SimpleDateFormat sdf = new SimpleDateFormat(TimeService.DEFAULT_FORMAT);
        try {
            Date date = sdf.parse(timeString);
            assertNotNull(date);
        } catch (ParseException e) {
            fail(e.getMessage());
        }

    }

}
