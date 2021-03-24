package io.kyberorg.example.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.service.StatusService;
import io.kyberorg.example.util.SystemStatus;

@SpringComponent
@UIScope
@PageTitle("ExampleApp: Status Page")
@Route(value = "", layout = MainView.class)
public class StatusView extends VerticalLayout {

    private static final String UNKNOWN = "UNKNOWN";

    private final StatusService statusService;
    private H2 title;
    private HorizontalLayout applicationStatusLine;
    private HorizontalLayout databaseStatusLine;

    private Text applicationStatusText;
    private Text databaseStatusText;

    public StatusView(StatusService statusService) {
        this.statusService = statusService;

        init();
        applyLoadState();
    }

    private void init() {
        this.setId(IDs.VIEW_ID);
        initTitle();
        initApplicationStatusLine();
        initDatabaseStatusLine();

        add(title, applicationStatusLine, databaseStatusLine);
    }

    private void applyLoadState() {
        SystemStatus applicationStatus = statusService.getApplicationStatus();
        SystemStatus databaseStatus = statusService.getDatabaseStatus();

        if(applicationStatus == SystemStatus.UP && databaseStatus == SystemStatus.UP) {
            title.setText("It works!");
        } else if (applicationStatus == SystemStatus.DOWN || databaseStatus == SystemStatus.DOWN) {
            title.setText("Some stuff is broken");
        } else {
            title.setText("App is DOWN");
        }

        applicationStatusText.setText(applicationStatus.name());
        databaseStatusText.setText(databaseStatus.name());
    }

    private void initTitle() {
        title = new H2("It works!");
    }

    private void initApplicationStatusLine() {
        applicationStatusLine = new HorizontalLayout();
        Span text = new Span("Application Status: ");
        applicationStatusText = new Text(UNKNOWN);
        applicationStatusLine.add(text, applicationStatusText);
    }

    private void initDatabaseStatusLine() {
        databaseStatusLine = new HorizontalLayout();
        Span text = new Span("Database Status: ");
        databaseStatusText = new Text(UNKNOWN);
        databaseStatusLine.add(text, databaseStatusText);
    }

    public static class IDs {
        public static final String VIEW_ID = "statusView";
    }
}
