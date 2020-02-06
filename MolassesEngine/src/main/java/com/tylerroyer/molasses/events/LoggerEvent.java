package com.tylerroyer.molasses.events;

public class LoggerEvent implements Event {
    private String msg;

    public LoggerEvent(String msg) {
        this.msg = msg;
    }

    @Override
    public void doAction() {
        System.out.println("LoggerEvent activated: " + msg);
    }
}
