package com.tylerroyer.enginetesting;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import com.tylerroyer.molasses.Button;
import com.tylerroyer.molasses.Game;
import com.tylerroyer.molasses.GameGraphics;
import com.tylerroyer.molasses.Screen;
import com.tylerroyer.molasses.events.*;

public class MainScreen extends Screen {
    Screen eventTestScreen;
    Button eventTestButton;

    public MainScreen() {
        eventTestScreen = new EventTestScreen();
    }

    @Override
    public void onFocus() {
        Font font = new Font("Helvetica", Font.PLAIN, 12);
        Event changeScreenEvent = new ChangeScreenEvent(eventTestScreen);
        eventTestButton = new Button("Test events", font, new Color(128, 0, 0), new Color(175, 0, 0), new Color(175, 50, 50), Color.WHITE,
              150, 50, 25, 10, changeScreenEvent);

        Game.getKeyboardHandler().addTypedEvent(KeyEvent.VK_W, new LoggerEvent("W key down."));
    }

    @Override
    public void update() {
        eventTestButton.update();
    }

    @Override
    public void render(GameGraphics g) {
        eventTestButton.render(g);
    }
    
}