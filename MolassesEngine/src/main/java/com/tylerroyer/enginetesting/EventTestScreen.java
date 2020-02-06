package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;
import com.tylerroyer.molasses.events.ChangeScreenEvent;
import com.tylerroyer.molasses.events.DecrementIntegerEvent;
import com.tylerroyer.molasses.events.Event;
import com.tylerroyer.molasses.events.IncrementIntegerEvent;
import com.tylerroyer.molasses.events.ToggleEvent;

import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;

import java.awt.Color;
import java.awt.Font;

class EventTestScreen extends Screen {
    Button toggleButton, decButton, incButton, mainScreenButton;
    MutableBoolean state = new MutableBoolean(false);
    MutableInt value = new MutableInt(0);

    public EventTestScreen() {
    }

    @Override
    public void onFocus() {
        Font font = new Font("Helvetica", Font.PLAIN, 12);
        Event toggleEvent = new ToggleEvent(state);
        toggleButton = new Button("Toggle", font, new Color(128, 0, 0), Color.WHITE, 75, 50, 20, 20, toggleEvent);
        
        Event decEvent = new DecrementIntegerEvent(value, 5, -50);
        Event incEvent = new IncrementIntegerEvent(value, 5, 53);
        decButton = new Button("-5", font, new Color(128, 0, 0), Color.WHITE, 40, 40, 20, 90, decEvent);
        incButton = new Button("+5", font, new Color(128, 0, 0), Color.WHITE, 40, 40, 110, 90, incEvent);
        
        mainScreenButton = new Button("Return to main screen", font, new Color(128, 0, 0), Color.WHITE,
              150, 50, 800, 20, new ChangeScreenEvent(new MainScreen()));
    }

    @Override
    public void update() {
        toggleButton.update();
        incButton.update();
        decButton.update();
        mainScreenButton.update();
    }

    @Override
    public void render(GameGraphics g) {
        toggleButton.render(g);
        g.setColor(Color.WHITE);
        g.drawString(state.toString(), 120, 70);

        decButton.render(g);
        incButton.render(g);
        g.setColor(Color.WHITE);
        g.drawString(value.toString(), 70, 115);

        mainScreenButton.render(g);
    }
}