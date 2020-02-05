package com.tylerroyer.molasses.events;

public class SetStringEvent implements Event {
    private StringBuffer value;
    private String setTo;

    public SetStringEvent(StringBuffer value, String setTo) {
        this.value = value;
        this.setTo = setTo;
    }

    @Override
    public void doAction() {
        value.delete(0, value.length());
        value.insert(0, setTo);
    }
}