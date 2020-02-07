package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class ToggleEvent extends Event {
    private MutableBoolean state;

    public ToggleEvent(MutableBoolean state) {
        this.state = state;
    }

    @Override
    protected void performAction() {
        state.setValue(!state.getValue());
    }
}