package io.kyberorg.example;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Inline;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Example Application.
 *
 * @since 1.0
 */
@SpringBootApplication
@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@PWA(
        name = "Example App",
        shortName = "example",
        offlinePath = "offline-page.html",
        offlineResources = {"images/logo.png"},
        description = "Simple Spring Boot Application with REST, Vaadin and Database ")
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
public class ExampleApplication extends SpringBootServletInitializer implements AppShellConfigurator {

    /**
     * Start Point.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Override
    public void configurePage(final AppShellSettings settings) {
        settings.addFavIcon("icon", "/icons/favicon-32x32.png", "32x32");
        settings.addLink("shortcut icon", "/icons/favicon-16x16.png");
        settings.addLink("apple-touch-icon", "/icons/apple-touch-icon.png");
        settings.addLink("manifest", "/site.webmanifest");
        settings.addLink("mask-icon", "/icons/safari-pinned-tab.svg");

        settings.addMetaTag("apple-mobile-web-app-title", "example-app");
        settings.addMetaTag("application-name", "example-app");
        settings.addMetaTag("msapplication-TileColor", "#ffc40d");
        settings.addMetaTag("theme-color", "#000000");

        //Splash (Loading) Screen
        settings.addInlineFromFile("splash-screen.html", Inline.Wrapping.NONE);
    }
}
