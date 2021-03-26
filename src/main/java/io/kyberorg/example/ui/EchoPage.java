package io.kyberorg.example.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;
import org.apache.commons.lang3.StringUtils;


@SpringComponent
@UIScope
@PageTitle("ExampleApp: Echo Page")
@Route(value = Endpoint.UI.ECHO_PAGE, layout = MainView.class)
public class EchoPage extends VerticalLayout {
    private final H2 title = new H2("Echo Page");
    private final Span aboutSpan = new Span("This page implements Echo Protocol aka TCP/7. " +
            "We send received data back.");

    private final HorizontalLayout inputLine = new HorizontalLayout();
    private final Span inputSpan = new Span("Enter something...");
    private final TextField input = new TextField();
    private final Button sendButton = new Button("Send");

    private final HorizontalLayout resultLine = new HorizontalLayout();
    private final Span resultLabelSpan = new Span("Result:");
    private final Span resultSpan = new Span();

    public EchoPage() {
        init();
        initControls();
    }

    private void init() {
        aboutSpan.setId(IDs.ABOUT_SECTION);
        input.setId(IDs.INPUT);
        sendButton.setId(IDs.SEND_BUTTON);
        resultSpan.setId(IDs.RESULT_SPAN);

        inputLine.add(inputSpan, input, sendButton);
        resultLine.add(resultLabelSpan, resultSpan);

        add(title, aboutSpan, inputLine, resultLine);
    }

    private void initControls() {
        sendButton.addClickListener(this::onSend);
    }

    private void onSend(ClickEvent<Button> buttonClickEvent) {
        String inputValue = input.getValue();
        if(StringUtils.isNotBlank(inputValue)) {
            resultSpan.setText(inputValue);
            input.setValue("");
        } else {
            showError("Nothing to send...");
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void showError(final String errorText) {
        Notification errorBox = new Notification();
        errorBox.addThemeVariants(NotificationVariant.LUMO_ERROR);
        errorBox.setPosition(Notification.Position.MIDDLE);

        HorizontalLayout layout = new HorizontalLayout();
        Label label = new Label(errorText);
        Button closeButton = new Button("OK", event -> errorBox.close());

        label.getStyle().set("margin-right", "0.5rem");
        closeButton.getStyle().set("margin-right", "0.5rem");

        errorBox.setId(IDs.ERROR_BOX);
        label.setId(IDs.ERROR_TEXT);
        closeButton.setId(IDs.ERROR_BUTTON);

        layout.add(label, closeButton);
        errorBox.add(layout);

        errorBox.open();
    }

    private static class IDs {
        public static final String ABOUT_SECTION = "about";
        public static final String INPUT = "echoInput";
        public static final String SEND_BUTTON = "sendButton";
        public static final String RESULT_SPAN = "resultSpan";

        public static final String ERROR_BOX = "errorBox";
        public static final String ERROR_TEXT = "errorText";
        public static final String ERROR_BUTTON = "errorButton";
    }
}
