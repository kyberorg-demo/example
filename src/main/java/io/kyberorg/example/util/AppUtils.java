package io.kyberorg.example.util;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Application utilities.
 *
 * @since 1.0
 */
@Component
public class AppUtils {
    @Getter
    private final Environment env;

    /**
     * Creates {@link AppUtils}.
     *
     * @param env environment variables
     */
    public AppUtils(final Environment env) {
        this.env = env;
    }

    /**
     * Shows error banner with given text.
     *
     * @param errorText text of error to show.
     * @return error banner object
     */
    public static Notification showError(final String errorText) {
        Notification errorBox = new Notification();
        errorBox.addThemeVariants(NotificationVariant.LUMO_ERROR);
        return getNotification(errorText, errorBox);
    }

    /**
     * Shows successful banner.
     *
     * @param text text to show (optional).
     * @return success banner.
     */
    public static Notification showSuccess(final Optional<String> text) {
        String bannerText = text.orElse("Operation successful");
        Notification successBox = new Notification();
        successBox.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        return getNotification(bannerText, successBox);
    }

    private static Notification getNotification(final String bannerText, final Notification notification) {
        notification.setPosition(Notification.Position.MIDDLE);

        HorizontalLayout layout = new HorizontalLayout();
        Label label = new Label(bannerText);
        Button closeButton = new Button("OK", event -> notification.close());

        label.getStyle().set("margin-right", "0.5rem");
        closeButton.getStyle().set("margin-right", "0.5rem");

        layout.add(label, closeButton);
        notification.add(layout);

        return notification;
    }

}
