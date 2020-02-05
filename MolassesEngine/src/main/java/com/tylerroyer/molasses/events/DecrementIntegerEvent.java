package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableInt;

public class DecrementIntegerEvent implements Event {
    private MutableInt value;
    private int step;
    private int min;

    public DecrementIntegerEvent(MutableInt value, int step, int min) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive.");

        this.value = value;
        this.step = step;
        this.min = min;
    }

    public DecrementIntegerEvent(MutableInt value, int step) {
        this(value, step, Integer.MIN_VALUE);
    }

    @Override
    public void doAction() {
        if (min + step > value.getValue()) {
            value.setValue(min);
            return;
        }

        value.add(-step);
    }
}