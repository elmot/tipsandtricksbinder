package org.vaadin.tipsandtricks.binder;

import com.vaadin.data.HasValue;
import com.vaadin.shared.Registration;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

public class LabelForBinder extends Label implements HasValue<String>{
    public LabelForBinder() {
    }

    public LabelForBinder(String text) {
        super(text);
    }

    public LabelForBinder(String text, ContentMode contentMode) {
        super(text, contentMode);
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<String> listener) {
        return (Registration) () -> {
        };
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        if(!readOnly) throw new IllegalArgumentException("Not Writable");
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        if(requiredIndicatorVisible) throw new IllegalArgumentException("Not Writable");
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return false;
    }
}
