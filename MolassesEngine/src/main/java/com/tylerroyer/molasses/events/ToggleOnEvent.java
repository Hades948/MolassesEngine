package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class ToggleOnEvent implements Event {
    private MutableBoolean state;

    public ToggleOnEvent(MutableBoolean state) {
        this.state = state;
    }

    @Override
    public void doAction() {
        state.setValue(true);
    }
}