package io.kyberorg.example.tests.ui.pageobjects;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;

/**
 * Common Vaadin elements and methods.
 *
 * @since 2.7.4
 */
public final class Vaadin {

    public static final SelenideElement LOADING_BAR = $(".v-loading-indicator");

    /**
     * Ensures that site is loaded and Vaadin loading bar already disappear.
     */
    public static void waitForVaadin() {
        $(LOADING_BAR).shouldBe(hidden, Duration.ofMillis(Configuration.timeout));
    }

    private Vaadin() {
        throw new UnsupportedOperationException("Utility class");
    }
}
