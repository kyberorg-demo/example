package io.kyberorg.example.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import io.kyberorg.example.App;
import io.kyberorg.example.util.AppUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Application layout with (NavBar, Menu and Content) View.
 *
 * @since 1.0
 */
//@SpringComponent
//@UIScope
@CssImport("./css/main_view.css")
public class MainView extends AppLayout implements BeforeEnterObserver {

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
        addMenuTab("Settings", SettingsPage.class, VaadinIcon.COG);

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

    public static class IDs {
        public static final String VIEW_ID = "mainView";
        public static final String APP_LOGO = "appLogo";
    }

}
