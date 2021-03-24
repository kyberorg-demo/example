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
@PageTitle("ExampleApp: Echo Page")
@Route(value = Endpoint.UI.ECHO_PAGE, layout = MainView.class)
public class EchoPage extends Div {
    public EchoPage() {
        add(new H2("Echo Page"));
    }
}
