package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;
import com.tylerroyer.molasses.events.Event;
import com.tylerroyer.molasses.events.ToggleEvent;

import org.apache.commons.lang3.mutable.MutableBoolean;

import java.awt.Color;
import java.awt.Font;

class TestScreen extends Screen {
    Button colorButton;
    MutableBoolean state = new MutableBoolean(false);

    public TestScreen() {
    }

    @Override
    public void loadResources() {
        Font font = new Font("Helvetica", Font.PLAIN, 12);
        Event event = new ToggleEvent(state);
        colorButton = new Button("Test", font, Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, 50, 50, 25, 10, event);

    }

    @Override
    public void update() {
        colorButton.update();
    }

    @Override
    public void render(GameGraphics g) {
        colorButton.render(g);
        g.setColor(Color.WHITE);
        g.drawString(state.toString(), 100, 75);
    }
}