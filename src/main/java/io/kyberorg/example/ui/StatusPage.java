package io.kyberorg.example.ui;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;
import io.kyberorg.example.service.StatusService;
import io.kyberorg.example.util.SystemStatus;

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

    public StatusPage(StatusService statusService) {
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

        Icon q = new Icon(VaadinIcon.FLASK);
        q.setId("512px");
        q.setColor("blue");

        add(title, applicationStatusLine, databaseStatusLine, randomComponentStatusLine, q);
    }

    private void applyLoadState() {
        SystemStatus applicationStatus = statusService.getApplicationStatus();
        SystemStatus databaseStatus = statusService.getDatabaseStatus();
        SystemStatus randomStatus = statusService.getRandomStatus();

        if(applicationStatus == SystemStatus.UP &&
                databaseStatus == SystemStatus.UP && randomStatus == SystemStatus.UP) {
            appendTitleWithText("It works!");
        } else if (applicationStatus == SystemStatus.DOWN ||
                databaseStatus == SystemStatus.DOWN || randomStatus == SystemStatus.DOWN) {
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
        applicationStatusLine.add(text, applicationStatusText);
    }

    private void initDatabaseStatusLine() {
        databaseStatusLine = new HorizontalLayout();
        Span text = new Span("Database Status: ");
        databaseStatusText = new Span(UNKNOWN);
        databaseStatusLine.add(text, databaseStatusText);
    }

    private void initRandomComponentStatusLine() {
        randomComponentStatusLine = new HorizontalLayout();
        Span text = new Span("Random Component Status: ");
        randomComponentStatusText = new Span(UNKNOWN);
        randomComponentStatusLine.add(text, randomComponentStatusText);
    }

    private void formatConditionally(HasStyle component, SystemStatus status) {
        if(status == SystemStatus.UP) {
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
    }
}
