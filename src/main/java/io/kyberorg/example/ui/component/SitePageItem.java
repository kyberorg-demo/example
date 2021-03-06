package io.kyberorg.example.ui.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ElementConstants;
import com.vaadin.flow.router.RouterLink;
import io.kyberorg.example.ui.HomePage;
import lombok.EqualsAndHashCode;

/**
 * Vaadin's component for {@link HomePage}.
 *
 * @since 1.0
 */
@CssImport("./css/site_page_item.css")
@EqualsAndHashCode(callSuper = true)
public class SitePageItem extends Composite<RouterLink> {
    private final VaadinIcon icon;
    private final String text;
    private final Class<? extends Component> target;

    private final VerticalLayout content = new VerticalLayout();
    private Icon i;
    private Span textSpan;

    /**
     * Spring's constructor.
     *
     * @param icon {@link VaadinIcon} to display within component.
     * @param text string with text to display within component.
     * @param target page to redirect to.
     */
    public SitePageItem(final VaadinIcon icon, final String text, final Class<? extends Component> target) {
        this.icon = icon;
        this.text = text;
        this.target = target;

        init();
        addStyle();
    }

    private void init() {
        i = new Icon(icon);
        textSpan = new Span(text);

        content.add(i, textSpan);
        getContent().add(content);
        getContent().setRoute(target);
    }

    private void addStyle() {
        i.addClassName("site-page-item-content-icon");
        i.getStyle().set(ElementConstants.STYLE_MAX_HEIGHT, "100%");
        textSpan.addClassName("site-page-item-content-text");

        content.addClassName("site-page-item-content");
        content.setAlignItems(FlexComponent.Alignment.CENTER);

        getContent().addClassName("site-page-item");
    }
}
