package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableInt;

public class IntModificationEvent implements Event {
    MutableInt value;
    int step;
    int max, min;

    public IntModificationEvent(MutableInt value, int step, int max, int min) {
        if (step == 0) throw new IllegalArgumentException("Step cannot be zero.");

        this.value = value;
        this.step = step;
        this.max = max;
        this.min = min;
    }

    public IntModificationEvent(MutableInt value, int step) {
        this(value, step, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    @Override
    public void doAction() {
        if (step > 0 && value.getValue() == max) return;
        if (step < 0 && value.getValue() == min) return;

        value.add(step);
    }
}