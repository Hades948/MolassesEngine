package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableDouble;

public class IncrementDoubleEvent extends Event {
    private MutableDouble value;
    private double step;
    private double max;

    public IncrementDoubleEvent(MutableDouble value, double step, double max) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive.");

        this.value = value;
        this.step = step;
        this.max = max;
    }

    public IncrementDoubleEvent(MutableDouble value, double step) {
        this(value, step, Double.MAX_VALUE);
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