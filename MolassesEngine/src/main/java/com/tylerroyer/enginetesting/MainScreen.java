package com.tylerroyer.enginetesting;

import java.awt.Color;
import java.awt.Font;

import com.tylerroyer.molasses.Button;
import com.tylerroyer.molasses.FlipBook;
import com.tylerroyer.molasses.GameGraphics;
import com.tylerroyer.molasses.Screen;
import com.tylerroyer.molasses.events.*;

import org.apache.commons.lang3.mutable.MutableInt;

public class MainScreen extends Screen {
    Screen eventTestScreen, audioTestScreen;
    Button eventTestButton, audioTestButton;
    MutableInt tileX = new MutableInt(0);
    MutableInt tileY = new MutableInt(0);

    public MainScreen() {
        eventTestScreen = new EventTestScreen();
        audioTestScreen = new AudioTestScreen();
    }

    @Override
    public void onFocus() {
        Font font = new Font("Helvetica", Font.PLAIN, 12);
        eventTestButton = new Button(new FlipBook("test.mfb"), 25, 10, new ChangeScreenEvent(eventTestScreen));
        //eventTestButton = new Button("Test Events", font, new Color(128, 0, 0), Color.WHITE, 150, 50, 25, 10, new ChangeScreenEvent(eventTestScreen));
        audioTestButton = new Button("Test Audio", font, new Color(128, 0, 0), Color.WHITE, 150, 50, 200, 10, new ChangeScreenEvent(audioTestScreen));
    }

    @Override
    public void update() {
        eventTestButton.update();
        audioTestButton.update();
    }

    @Override
    public void render(GameGraphics g) {
        eventTestButton.render(g);
        audioTestButton.render(g);
    }
    
}