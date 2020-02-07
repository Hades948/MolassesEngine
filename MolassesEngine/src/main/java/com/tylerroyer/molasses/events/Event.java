package com.tylerroyer.molasses.events;

import java.util.ArrayList;

import org.apache.commons.lang3.mutable.MutableBoolean;

public abstract class Event {
    ArrayList<MutableBoolean> conditions = new ArrayList<>();

    public boolean trigger() {
        boolean allConditionsMet = true;
        for (MutableBoolean condition : conditions) {
            if (condition.isFalse()) {
                allConditionsMet = false;
                break;
            }
        }

        if (allConditionsMet) {
            performAction();
        }

        return allConditionsMet;
    }

    public void addCondition(MutableBoolean condition) {
        conditions.add(condition);
    }

    public void clearConditions() {
        conditions.clear();
    }

    protected abstract void performAction();
}