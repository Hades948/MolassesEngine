package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableInt;

public class IncrementIntegerEvent implements Event {
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
    public void doAction() {
        if (max - step < value.getValue()) {
            value.setValue(max);
            return;
        }

        value.add(step);
    }
}