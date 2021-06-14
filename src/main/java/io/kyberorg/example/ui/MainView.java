package io.kyberorg.example.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import io.kyberorg.example.App;
import io.kyberorg.example.util.AppUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Application layout with (NavBar, Menu and Content) View.
 *
 * @since 1.0
 */
@SpringComponent
@UIScope
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@PWA(
        name = "Example App",
        shortName = "example",
        offlinePath = "offline-page.html",
        offlineResources = {"images/logo.png"},
        description = "Simple Spring Boot Application with REST, Vaadin and Database ")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("./css/main_view.css")
public class MainView extends AppLayout implements BeforeEnterObserver, PageConfigurator {

    private final Tabs tabs = new Tabs();
    private final Map<Class<? extends Component>, Tab> tabToTarget = new HashMap<>();

    /**
     * Creates Main Application (NavBar, Menu and Content) View.
     *
     * @param appUtils application utils for getting site title.
     */
    public MainView(final AppUtils appUtils) {
        String siteTitle = appUtils.getEnv().getProperty(App.Properties.APP_SITE_TITLE, "example").toUpperCase();

        DrawerToggle toggle = new DrawerToggle();
        setPrimarySection(Section.NAVBAR);

        Span title = new Span(siteTitle);
        title.addClassName("site-title");
        addToNavbar(toggle, title);

        //items
        addLogo();
        addSubTitle();
        addMenuTab("Home", HomePage.class, VaadinIcon.HOME);
        addMenuTab("Time", TimePage.class, VaadinIcon.CLOCK);
        addMenuTab("Echo", EchoPage.class, VaadinIcon.EXCHANGE);
        addMenuTab("Status", StatusPage.class, VaadinIcon.SPARK_LINE);
        addMenuTab("Unicom", UnicomPage.class, VaadinIcon.COMMENT);

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);

        setId(IDs.VIEW_ID);

        // hide the splash screen after the main view is loaded
        UI.getCurrent().getPage().executeJs(
                "document.querySelector('#splash-screen').classList.add('loaded')");
    }

    private void addLogo() {
        Image logo = new Image("/images/logo.png", "Icon");
        logo.setId(IDs.APP_LOGO);
        logo.addClassName("logo-image");
        Tab logoTab = new Tab(logo);
        logoTab.setEnabled(false);
        tabs.add(logoTab);
    }

    private void addSubTitle() {
        Tab subTitleTab = new Tab("Example Application");
        subTitleTab.setEnabled(false);
        subTitleTab.addClassName("subtitle-tab");
        tabs.add(subTitleTab);
    }

    private void addMenuTab(final String label, final Class<? extends Component> target, final VaadinIcon icon) {
        RouterLink link = new RouterLink(null, target);
        link.add(icon.create());
        link.add(label);
        link.setHighlightCondition(HighlightConditions.sameLocation());
        Tab tab = new Tab(link);
        tabToTarget.put(target, tab);
        tabs.add(tab);
    }

    @Override
    public void beforeEnter(final BeforeEnterEvent beforeEnterEvent) {
        tabs.setSelectedTab(tabToTarget.get(beforeEnterEvent.getNavigationTarget()));
    }

    @Override
    public void configurePage(final InitialPageSettings settings) {
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
        settings.addInlineFromFile("splash-screen.html", InitialPageSettings.WrapMode.NONE);
    }

    public static class IDs {
        public static final String VIEW_ID = "mainView";
        public static final String APP_LOGO = "appLogo";
    }

}
