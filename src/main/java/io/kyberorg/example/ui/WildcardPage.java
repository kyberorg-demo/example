package io.kyberorg.example.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.spring.annotation.SpringComponent;

import javax.servlet.http.HttpServletResponse;

@SpringComponent
@Tag(Tag.DIV)
public class WildcardPage extends Component implements HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        getElement().setText("Could not navigate to '"
                + event.getLocation().getPath()
                + "'");
        return HttpServletResponse.SC_NOT_FOUND;
    }
}
