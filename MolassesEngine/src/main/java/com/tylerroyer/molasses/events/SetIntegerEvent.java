package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableInt;

public class SetIntegerEvent implements Event {
    MutableInt value;
    int setTo;

    public SetIntegerEvent(MutableInt value, int setTo) {
        this.value = value;
        this.setTo = setTo;
    }

    @Override
    public void doAction() {
        value.setValue(setTo);
    }
}