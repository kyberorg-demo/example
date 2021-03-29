package io.kyberorg.example.ui;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;
import io.kyberorg.example.service.StatusService;
import io.kyberorg.example.util.SystemStatus;

/**
 * Status Page.
 *
 * @since 1.0
 */
@SpringComponent
@UIScope
@PageTitle("ExampleApp: Status Page")
@Route(value = Endpoint.UI.STATUS_PAGE, layout = MainView.class)
@CssImport("./css/status_page.css")
public class StatusPage extends VerticalLayout {

    private static final String UNKNOWN = "UNKNOWN";

    private final StatusService statusService;
    private H2 title;
    private HorizontalLayout applicationStatusLine;
    private HorizontalLayout databaseStatusLine;
    private HorizontalLayout randomComponentStatusLine;

    private Span applicationStatusText;
    private Span databaseStatusText;
    private Span randomComponentStatusText;

    /**
     * Creates Status Page.
     *
     * @param statusService services for getting component's status
     */
    public StatusPage(final StatusService statusService) {
        this.statusService = statusService;

        init();
        applyLoadState();
        applyStyle();
    }

    private void init() {
        this.setId(IDs.VIEW_ID);
        initTitle();
        initApplicationStatusLine();
        initDatabaseStatusLine();
        initRandomComponentStatusLine();

        add(title, applicationStatusLine, databaseStatusLine, randomComponentStatusLine);
    }

    private void applyLoadState() {
        SystemStatus applicationStatus = statusService.getApplicationStatus();
        SystemStatus databaseStatus = statusService.getDatabaseStatus();
        SystemStatus randomStatus = statusService.getRandomStatus();

        if (applicationStatus == SystemStatus.UP
                && databaseStatus == SystemStatus.UP && randomStatus == SystemStatus.UP) {
            appendTitleWithText("It works!");
        } else if (applicationStatus == SystemStatus.DOWN
                || databaseStatus == SystemStatus.DOWN || randomStatus == SystemStatus.DOWN) {
            appendTitleWithText("Some stuff is broken");
        } else {
            appendTitleWithText("App is DOWN");
        }

        applicationStatusText.setText(applicationStatus.name());
        databaseStatusText.setText(databaseStatus.name());
        randomComponentStatusText.setText(randomStatus.name());

        formatConditionally(applicationStatusText, applicationStatus);
        formatConditionally(databaseStatusText, databaseStatus);
        formatConditionally(randomComponentStatusText, randomStatus);
    }

    private void applyStyle() {
        applicationStatusText.addClassName("status");
        databaseStatusText.addClassName("status");
        randomComponentStatusText.addClassName("status");
    }

    private void initTitle() {
        title = new H2("It works!");
    }

    private void initApplicationStatusLine() {
        applicationStatusLine = new HorizontalLayout();
        Span text = new Span("Application Status: ");
        applicationStatusText = new Span(UNKNOWN);
        text.setId(IDs.APP_STATUS_LABEL);
        applicationStatusText.setId(IDs.APP_STATUS_TEXT);
        applicationStatusLine.add(text, applicationStatusText);
    }

    private void initDatabaseStatusLine() {
        databaseStatusLine = new HorizontalLayout();
        Span text = new Span("Database Status: ");
        databaseStatusText = new Span(UNKNOWN);
        text.setId(IDs.DB_STATUS_LABEL);
        databaseStatusText.setId(IDs.DB_STATUS_TEXT);
        databaseStatusLine.add(text, databaseStatusText);
    }

    private void initRandomComponentStatusLine() {
        randomComponentStatusLine = new HorizontalLayout();
        Span text = new Span("Random Component Status: ");
        randomComponentStatusText = new Span(UNKNOWN);
        text.setId(IDs.RANDOM_COMPONENT_STATUS_LABEL);
        randomComponentStatusText.setId(IDs.RANDOM_COMPONENT_STATUS_TEXT);
        randomComponentStatusLine.add(text, randomComponentStatusText);
    }

    private void formatConditionally(final HasStyle component, final SystemStatus status) {
        if (status == SystemStatus.UP) {
            component.addClassName("green");
        } else {
            component.addClassName("red");
        }
    }

    private void appendTitleWithText(final String text) {
        title.setText("System Status: " + text);
    }

    public static class IDs {
        public static final String VIEW_ID = "statusView";
        public static final String APP_STATUS_LABEL = "appStatusLabel";
        public static final String APP_STATUS_TEXT = "appStatusText";
        public static final String DB_STATUS_LABEL = "dbStatusLabel";
        public static final String DB_STATUS_TEXT = "dbStatusText";
        public static final String RANDOM_COMPONENT_STATUS_LABEL = "rndStatusLabel";
        public static final String RANDOM_COMPONENT_STATUS_TEXT = "rndStatusText";
    }
}
