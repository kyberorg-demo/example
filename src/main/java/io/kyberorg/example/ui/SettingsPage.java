package io.kyberorg.example.ui;

import com.vaadin.componentfactory.ToggleButton;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;
import io.kyberorg.example.model.Settings;
import io.kyberorg.example.service.SettingsService;
import io.kyberorg.example.util.AppUtils;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@SpringComponent
@UIScope
@PageTitle("ExampleApp: Settings Page")
@Route(value = Endpoint.UI.SETTINGS_PAGE, layout = MainView.class)
public class SettingsPage extends VerticalLayout implements BeforeEnterObserver {
    private static final String UNDEFINED = "UNDEFINED";

    private final SettingsService settingsService;

    private final Span sessionSpan = new Span();
    private final ToggleButton lightModeToggle = new ToggleButton("LightMode");

    private boolean lightMode = false;
    private Settings settings;

    @Override
    public void beforeEnter(final BeforeEnterEvent event) {
        final String sessionId = getSessionId();
        sessionSpan.setText("Session ID: " + sessionId);

        if (!sessionId.equals(UNDEFINED)) {
            settings = settingsService.getSettings(sessionId);
            if (settings == null) {
                settings = Settings.createNewForSession(sessionId);
                settingsService.save(settings);
            }
            lightMode = settings.isLightMode();
        }

        lightModeToggle.setValue(lightMode);
        lightModeToggle.addValueChangeListener(this::onLightModeChanged);

        setTheme(lightMode);

        removeAll();
        add(sessionSpan, lightModeToggle);
    }


    private void onLightModeChanged(final AbstractField.ComponentValueChangeEvent<ToggleButton, Boolean> event) {
        boolean lightMode = event.getValue();
        if (hasSession()) {
            settings.setLightMode(lightMode);
            settingsService.save(settings);
        } else {
            this.lightMode = lightMode;
        }
        AppUtils.showSuccess(Optional.of("Saved"));
        setTheme(lightMode);
    }

    private void setTheme(final boolean setLightTheme) {
        UI ui = getUI().isPresent() ? getUI().get() : UI.getCurrent();
        if (ui == null) return;

        String theme = setLightTheme ? "light" : "dark";
        ui.getPage().executeJs("document.documentElement.setAttribute(\"theme\",\"" + theme + "\")");
    }

    private boolean hasSession() {
        return VaadinSession.getCurrent() != null && VaadinSession.getCurrent().getSession() != null;
    }

    private String getSessionId() {
        final String sessionId;
        if (hasSession()) {
            sessionId = VaadinSession.getCurrent().getSession().getId();
        } else {
            sessionId = UNDEFINED;
        }
        return sessionId;
    }
}
