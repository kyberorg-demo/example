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
}
