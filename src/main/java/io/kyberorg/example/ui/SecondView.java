package io.kyberorg.example.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@SpringComponent
@UIScope
@PageTitle("ExampleApp: Second Page")
@Route(value = "pageTwo", layout = MainView.class)
public class SecondView extends Div {
    public SecondView() {
        add(new H2("It also works!"));
    }
}
