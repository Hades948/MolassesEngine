package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableDouble;

public class MultiplyDoubleEvent extends Event {
    private MutableDouble value;
    private double factor;

    public MultiplyDoubleEvent(MutableDouble value, double factor) {
        this.value = value;
        this.factor = factor;
    }

    @Override
    protected void performAction() {
        value.setValue(value.getValue() * factor);
    }
}