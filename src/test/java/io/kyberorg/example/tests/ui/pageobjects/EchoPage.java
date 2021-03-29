package io.kyberorg.example.tests.ui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public final class EchoPage {

    private EchoPage() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final SelenideElement PAGE_TITLE = $("h2");

    public static final SelenideElement ABOUT_SECTION = $("#about");
    public static final SelenideElement INPUT = $("#echoInput");
    public static final SelenideElement SEND_BUTTON = $("#sendButton");
    public static final SelenideElement RESULT_SPAN = $("#resultSpan");

}
