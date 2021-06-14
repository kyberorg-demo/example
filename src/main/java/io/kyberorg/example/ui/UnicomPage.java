package io.kyberorg.example.ui;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.App;
import io.kyberorg.example.Endpoint;
import io.kyberorg.example.event.RecordSavedEvent;
import io.kyberorg.example.model.Record;
import io.kyberorg.example.service.RecordService;
import io.kyberorg.example.util.AppUtils;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;
import java.util.Optional;

/**
 * Unicom Page.
 *
 * @since 1.0
 */
@SpringComponent
@UIScope
@PageTitle("ExampleApp: Unicom Page")
@Route(value = Endpoint.UI.UNICOM_PAGE, layout = MainView.class)
public class UnicomPage extends VerticalLayout {
    private final H2 title = new H2("Unicom Page");
    private final Span aboutSpan = new Span("This page simulates Unicom Channel. Unicom never replies.");

    private final HorizontalLayout nameLine = new HorizontalLayout();
    private final Span nameLabel = new Span("Enter your name: ");
    private final TextField nameInput = new TextField();
    private final Span nameSpan = new Span();
    private final Button saveNameButton = new Button("Save");

    private final HorizontalLayout inputLine = new HorizontalLayout();
    private final Span inputSpan = new Span("Enter something: ");
    private final TextField input = new TextField();
    private final Button sendButton = new Button("Send");

    private final Details broadcast = new Details("Broadcast", new Text(App.C.EMPTY_BROADCAST_MESSAGE));

    private final RecordService recordService;
    private int messageCounter = 0;

    /**
     * Creates unicom page.
     *
     * @param recordService service for writing records.
     */
    public UnicomPage(final RecordService recordService) {
        this.recordService = recordService;

        init();
        initControls();
        initState();
    }

    private void init() {
        aboutSpan.setId(IDs.ABOUT_SECTION);
        input.setId(IDs.INPUT);
        sendButton.setId(IDs.SEND_BUTTON);

        nameLine.add(nameLabel, nameSpan, nameInput, saveNameButton);
        inputLine.add(inputSpan, input, sendButton);

        add(title, aboutSpan, nameLine, inputLine, broadcast);
    }

    private void initControls() {
        saveNameButton.addClickListener(this::onNameSave);
        sendButton.addClickListener(this::onSend);
    }

    private void initState() {
        nameSpan.setVisible(false);
        input.setEnabled(false);
        sendButton.setEnabled(false);

        broadcast.setOpened(true);
    }

    private void onNameSave(final ClickEvent<Button> buttonClickEvent) {
        String nameValue = nameInput.getValue();
        if (StringUtils.isNotBlank(nameValue)) {
            nameLabel.setText("Name:");
            nameSpan.setText(nameValue);

            nameInput.setVisible(false);
            saveNameButton.setVisible(false);
            nameSpan.setVisible(true);

            input.setEnabled(true);
            sendButton.setEnabled(true);
        } else {
            showError("Empty name is not valid");
        }
    }

    private void onSend(final ClickEvent<Button> buttonClickEvent) {
        String author = nameSpan.getText();
        String inputValue = input.getValue();
        if (StringUtils.isBlank(author)) {
            showError("Name should be present as well.");
        }

        if (StringUtils.isNotBlank(inputValue)) {
            boolean written = recordService.writeRecord(author, inputValue);
            if (written) {
                showSuccess("Written");
            } else {
                showError("Failed to save record");
            }
            input.setValue("");
        } else {
            showError("Nothing to send...");
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onNewRecordSaved(final RecordSavedEvent event) {
        messageCounter++;
        broadcast.setSummaryText("Broadcast (" + messageCounter + ")");

        //clean default record
        Optional<Component> firstComponent = broadcast.getContent().findFirst();
        if(firstComponent.isPresent() && firstComponent.get() instanceof Text) {
            broadcast.setContent(new Div());
        }
        Record record = event.getSavedRecord();
        if(Objects.nonNull(record)) {
            broadcast.addContent(new Div(new Text(record.getAuthor() + ": " + record.getRecord())));
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void showSuccess(final String text) {
        Notification successBox = AppUtils.showSuccess(Optional.of(text));
        successBox.open();
    }

    private void showError(final String errorText) {
        Notification errorBox = AppUtils.showError(errorText);
        errorBox.open();
    }

    private static class IDs {
        public static final String ABOUT_SECTION = "about";
        public static final String INPUT = "unicomInput";
        public static final String SEND_BUTTON = "sendButton";
    }
}
