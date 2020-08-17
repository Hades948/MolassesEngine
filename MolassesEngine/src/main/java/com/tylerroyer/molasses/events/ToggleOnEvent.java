package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class ToggleOnEvent extends Event {
    private MutableBoolean state;

    public ToggleOnEvent(MutableBoolean state) {
        this.state = state;
    }

    @Override
    protected void performAction() {
        state.setValue(true);
    }
}