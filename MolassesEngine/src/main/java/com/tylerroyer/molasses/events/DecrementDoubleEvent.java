package com.tylerroyer.molasses.events;

import org.apache.commons.lang3.mutable.MutableDouble;

public class DecrementDoubleEvent extends Event {
    private MutableDouble value;
    private double step;
    private double min;

    public DecrementDoubleEvent(MutableDouble value, double step, double min) {
        if (step <= 0) throw new IllegalArgumentException("Step must be positive.");

        this.value = value;
        this.step = step;
        this.min = min;
    }

    public DecrementDoubleEvent(MutableDouble value, double step) {
        this(value, step, Double.MIN_VALUE);
    }

    @Override
    protected void performAction() {
        if (min + step > value.getValue()) {
            value.setValue(min);
            return;
        }

        value.add(-step);
    }
}