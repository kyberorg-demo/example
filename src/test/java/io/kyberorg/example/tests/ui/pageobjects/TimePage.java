package io.kyberorg.example.tests.ui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page object for Time Page.
 *
 * @since 1.0
 */
public final class TimePage {

    private TimePage() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final SelenideElement PAGE_TITLE = $("h2");
    public static final SelenideElement ABOUT_SPAN = $("#aboutTime");
    public static final SelenideElement TIME_LABEL = $("#timeLabel");
    public static final SelenideElement TIME_SPAN = $("#timeSpan");
}
