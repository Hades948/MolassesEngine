package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class ToggleOffEvent extends Event {
    private MutableBoolean state;

    public ToggleOffEvent(MutableBoolean state) {
        this.state = state;
    }

    @Override
    protected void performAction() {
        state.setValue(false);
    }
}