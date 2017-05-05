package org.vaadin.tipsandtricks;


import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.tipsandtricks.binder.LabelForBinder;
import org.vaadin.tipsandtricks.binder.ReadonlyHasValue;
import org.vaadin.tipsandtricks.pojo.PojoOne;

import javax.servlet.annotation.WebServlet;

public class TipsAndTricksUI extends UI {

    protected void init(VaadinRequest vaadinRequest) {
        AbstractComponent labelsLayout = setupLabels();

        setContent(new VerticalLayout(labelsLayout));
    }

    private AbstractComponent setupLabels() {
        VerticalLayout labelsLayout = new VerticalLayout();
        labelsLayout.addStyleName(ValoTheme.LAYOUT_WELL);
        labelsLayout.setCaption("Label and Binder");

        Binder<PojoOne> pojoOneBinder = new Binder<>();

        //Approach #1
        TextField fieldAsLabel = new TextField("Read-only TextField");
        fieldAsLabel.setReadOnly(true);
        pojoOneBinder.forField(fieldAsLabel).bind(PojoOne::getTextForLabel1, null);
        labelsLayout.addComponent(fieldAsLabel);

        //Approach #2
        LabelForBinder labelForBinder = new LabelForBinder();
        labelForBinder.setContentMode(ContentMode.HTML);
        labelForBinder.setCaption("Smart Label");
        pojoOneBinder.forField(labelForBinder).bind(PojoOne::getTextForLabel2, null);
        labelsLayout.addComponent(labelForBinder);

        //Approach #3
        Label label = new LabelForBinder();
        label.setCaption("Label with ReadonlyHasValue");
        label.setContentMode(ContentMode.HTML);
        ReadonlyHasValue<String> readonlyHasValue = new ReadonlyHasValue<String>(label::getValue, label::setValue);
        pojoOneBinder.forField(readonlyHasValue).bind(PojoOne::getTextForLabel3, null);
        labelsLayout.addComponent(label);
        pojoOneBinder.readBean(new PojoOne());
        return labelsLayout;

    }

    @VaadinServletConfiguration(ui = TipsAndTricksUI.class, productionMode = false)
    @WebServlet(value = "/*", asyncSupported = true)
    public static class TipsAndTricksServlet extends VaadinServlet {
    }
}

