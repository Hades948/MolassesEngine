package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableInt;

public class IncrementIntegerEvent extends Event {
    private MutableInt value;
    private int step;
    private int max;

    public IncrementIntegerEvent(MutableInt value, int step, int max) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive.");

        this.value = value;
        this.step = step;
        this.max = max;
    }

    public IncrementIntegerEvent(MutableInt value, int step) {
        this(value, step, Integer.MAX_VALUE);
    }

    @Override
    protected void performAction() {
        if (max - step < value.getValue()) {
            value.setValue(max);
            return;
        }

        value.add(step);
    }
}