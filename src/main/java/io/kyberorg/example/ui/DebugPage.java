package io.kyberorg.example.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;


/**
 * Status Page.
 *
 * @since 1.0
 */
@SpringComponent
@UIScope
@PageTitle("ExampleApp: Debug Page")
@Route(value = Endpoint.UI.DEBUG_PAGE, layout = MainView.class)
public class DebugPage extends VerticalLayout {

    /**
     * Creates Debug Page.
     */
    public DebugPage() {
        Button theButton = new Button();
        theButton.setText("Open Google in new tab");
        theButton.addClickListener(event -> {
           if (getUI().isPresent()) {
               getUI().get().getPage().open("https://google.com");
           }
        });
        Text text = new Text("It works!");
        add(theButton, text);
    }



}
