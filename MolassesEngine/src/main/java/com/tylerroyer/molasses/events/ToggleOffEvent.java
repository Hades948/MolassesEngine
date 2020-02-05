package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableBoolean;

public class ToggleOffEvent implements Event {
    private MutableBoolean state;

    public ToggleOffEvent(MutableBoolean state) {
        this.state = state;
    }

    @Override
    public void doAction() {
        state.setValue(false);
    }
}