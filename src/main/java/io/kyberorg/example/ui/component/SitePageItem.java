package io.kyberorg.example.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;

@CssImport("./css/site_page_item.css")
@EqualsAndHashCode(callSuper = true)
@Data
public class SitePageItem extends Composite<Div> {
    private VaadinIcon icon;
    private String text;
    private Class<? extends Component> target;

    private Image image;
    private Icon i;
    private Span textSpan;

    public SitePageItem(VaadinIcon icon, String text, Class<? extends Component> target) {
        this.icon = icon;
        this.text = text;
        this.target = target;

        init();
    }

    private void init() {
        i = new Icon(icon);
        i.addClassName("site-page-item-content-icon");
        textSpan = new Span(text);
        textSpan.addClassName("ite-page-item-content-text");

        VerticalLayout content = new VerticalLayout();
        content.add(i, textSpan);
        content.addClassName("site-page-item-content");
        content.setAlignItems(FlexComponent.Alignment.CENTER);

        getContent().add(content);
        getContent().addClassName("site-page-item");
    }

}
