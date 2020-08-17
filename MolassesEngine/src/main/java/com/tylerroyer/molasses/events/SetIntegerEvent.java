package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableInt;

public class SetIntegerEvent extends Event {
    private MutableInt value;
    private int setTo;

    public SetIntegerEvent(MutableInt value, int setTo) {
        this.value = value;
        this.setTo = setTo;
    }

    @Override
    protected void performAction() {
        value.setValue(setTo);
    }
}