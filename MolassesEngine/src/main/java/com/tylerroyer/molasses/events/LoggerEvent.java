package com.tylerroyer.molasses.events;

public class LoggerEvent extends Event {
    private String msg;

    public LoggerEvent(String msg) {
        this.msg = msg;
    }

    @Override
    protected void performAction() {
        System.out.println("LoggerEvent activated: " + msg);
    }
}
