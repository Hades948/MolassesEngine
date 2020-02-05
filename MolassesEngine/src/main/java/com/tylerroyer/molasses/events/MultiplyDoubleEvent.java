package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableDouble;

public class MultiplyDoubleEvent implements Event {
    private MutableDouble value;
    private double factor;

    public MultiplyDoubleEvent(MutableDouble value, double factor) {
        this.value = value;
        this.factor = factor;
    }

    @Override
    public void doAction() {
        value.setValue(value.getValue() * factor);
    }
}