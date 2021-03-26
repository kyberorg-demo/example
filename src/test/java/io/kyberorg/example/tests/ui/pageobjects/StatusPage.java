package io.kyberorg.example.tests.ui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page object for Status Page.
 *
 * @since 1.0
 */
public class StatusPage {

    private StatusPage() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final SelenideElement PAGE_TITLE = $("h2");
    public static final SelenideElement APP_STATUS_LABEL = $("#appStatusLabel");
    public static final SelenideElement APP_STATUS_TEXT = $("#appStatusText");
    public static final SelenideElement DB_STATUS_LABEL = $("#dbStatusLabel");
    public static final SelenideElement DB_STATUS_TEXT = $("#dbStatusText");
    public static final SelenideElement RANDOM_COMPONENT_STATUS_LABEL = $("#rndStatusLabel");
    public static final SelenideElement RANDOM_COMPONENT_STATUS_TEXT = $("#rndStatusText");
}
