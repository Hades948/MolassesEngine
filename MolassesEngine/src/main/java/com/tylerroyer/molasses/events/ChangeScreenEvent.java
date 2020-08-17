package com.tylerroyer.molasses.events;

import com.tylerroyer.molasses.*;

public class ChangeScreenEvent extends Event {
    private Screen newScreen;

    public ChangeScreenEvent(Screen newScreen) {
        this.newScreen = newScreen;
    }

    @Override
    protected void performAction() {
        Game.setCurrentScreen(newScreen);
    }
}