package io.kyberorg.example.tests.ui.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public final class HomePage {

    private HomePage() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final SelenideElement PAGE_TITLE = $("h2");
    public static final ElementsCollection SITE_PAGE_ITEMS = $$(".site-page-item");
}
