package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableDouble;

public class SetDoubleEvent extends Event {
    private MutableDouble value;
    private double setTo;

    public SetDoubleEvent(MutableDouble value, double setTo) {
        this.value = value;
        this.setTo = setTo;
    }

    @Override
    protected void performAction() {
        value.setValue(setTo);
    }
}