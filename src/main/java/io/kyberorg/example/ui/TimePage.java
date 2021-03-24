package io.kyberorg.example.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;

@SpringComponent
@UIScope
@PageTitle("ExampleApp: Time Page")
@Route(value = Endpoint.UI.TIME_PAGE, layout = MainView.class)
public class TimePage extends Div {
    public TimePage() {
        add(new H2("Time Page"));
    }
}
