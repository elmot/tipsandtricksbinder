package org.vaadin.tipsandtricks.binder;

import com.vaadin.data.HasValue;
import com.vaadin.server.SerializableConsumer;
import com.vaadin.server.SerializableSupplier;
import com.vaadin.shared.Registration;

public class ReadonlyHasValue<V> implements HasValue<V> {

    private final SerializableSupplier<V> getter;
    private final SerializableConsumer<V> setter;

    public ReadonlyHasValue(SerializableSupplier<V> getter, SerializableConsumer<V> setter) {
        this.getter = getter;
        this.setter = setter;
    }

    @Override
    public void setValue(V value) {
        setter.accept(value);
    }

    @Override
    public V getValue() {
        return getter.get();
    }

    @Override
    public Registration addValueChangeListener(ValueChangeListener<V> listener) {
        return (Registration) () -> {
        };
    }

    @Override
    public void setRequiredIndicatorVisible(boolean requiredIndicatorVisible) {
        if(requiredIndicatorVisible) throw new IllegalArgumentException("Not Writable");
    }

    @Override
    public boolean isRequiredIndicatorVisible() {
        return false;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        if(!readOnly) throw new IllegalArgumentException("Not Writable");
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

}
