package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class ToggleEvent implements Event {
    MutableBoolean state;

    public ToggleEvent(MutableBoolean state) {
        this.state = state;
    }

    @Override
    public void doAction() {
        state.setValue(!state.getValue());
    }
}