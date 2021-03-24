package io.kyberorg.example.ui;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import io.kyberorg.example.Endpoint;
import io.kyberorg.example.ui.component.SitePageItem;

@SpringComponent
@UIScope
@PageTitle("ExampleApp: Home Page")
@Route(value = Endpoint.UI.HOME_PAGE, layout = MainView.class)
public class HomePage extends VerticalLayout {
    public HomePage() {
        H2 title = new H2("Home Page");
        HorizontalLayout stuff = new HorizontalLayout();
        add(title, stuff);

        SitePageItem timePageItem = new SitePageItem(VaadinIcon.CLOCK, "Time", TimePage.class);
        SitePageItem echoPageItem = new SitePageItem(VaadinIcon.EXCHANGE, "Echo", EchoPage.class);
        SitePageItem statusPageItem = new SitePageItem(VaadinIcon.SPARK_LINE, "Status", StatusPage.class);
        stuff.add(timePageItem, echoPageItem, statusPageItem);
        stuff.setMaxWidth("100%");
    }
}
