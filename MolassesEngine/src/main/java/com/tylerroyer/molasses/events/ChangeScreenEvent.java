package com.tylerroyer.molasses.events;

import com.tylerroyer.molasses.*;

public class ChangeScreenEvent implements Event {
    Screen newScreen;

    public ChangeScreenEvent(Screen newScreen) {
        this.newScreen = newScreen;
    }

    @Override
    public void doAction() {
        Game.setCurrentScreen(newScreen);
    }
}