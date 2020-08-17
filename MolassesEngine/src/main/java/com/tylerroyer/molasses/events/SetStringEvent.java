package com.tylerroyer.molasses.events;

public class SetStringEvent extends Event {
    private StringBuffer value;
    private String setTo;

    public SetStringEvent(StringBuffer value, String setTo) {
        this.value = value;
        this.setTo = setTo;
    }

    @Override
    protected void performAction() {
        value.delete(0, value.length());
        value.insert(0, setTo);
    }
}