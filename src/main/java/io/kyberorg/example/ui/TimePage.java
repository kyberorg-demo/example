package io.kyberorg.example.ui;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;
import io.kyberorg.example.service.TimeService;

/**
 * Time Page.
 *
 * @since 1.0
 */
@SpringComponent
@UIScope
@PageTitle("ExampleApp: Time Page")
@Route(value = Endpoint.UI.TIME_PAGE, layout = MainView.class)
public class TimePage extends VerticalLayout {
    private final TimeService timeService;

    private final H2 title = new H2("Time Page");
    private final Span aboutSpan = new Span("This page implements Daytime Protocol aka TCP/13 aka RFC868. " +
            "Returns current time.");

    private final HorizontalLayout timeNowLine = new HorizontalLayout();
    private final Span timeLabel = new Span("Time now is:");
    private final Span timeSpan = new Span();

    /**
     * Creates Time Page.
     *
     * @param timeService service for getting time.
     */
    public TimePage(TimeService timeService) {
        this.timeService = timeService;
        init();
        initState();
    }

    private void init() {
        aboutSpan.setId(IDs.ABOUT_SPAN);
        timeLabel.setId(IDs.TIME_LABEL);
        timeSpan.setId(IDs.TIME_SPAN);

        timeNowLine.add(timeLabel, timeSpan);
        add(title, aboutSpan, timeNowLine);
    }

    private void initState() {
        timeSpan.setText(timeService.currentTime());
    }

    private static class IDs {
        public static final String ABOUT_SPAN = "aboutTime";
        public static final String TIME_LABEL = "timeLabel";
        public static final String TIME_SPAN = "timeSpan";
    }
}
