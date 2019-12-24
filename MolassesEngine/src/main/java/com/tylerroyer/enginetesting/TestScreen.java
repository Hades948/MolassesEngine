package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

class TestScreen extends Screen {
    Button testButton;
    public TestScreen() {}

    @Override
    public void loadResources() {
        Font font = new Font("Helvetica", Font.PLAIN, 32);
        testButton = new Button("Test", font, Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, 100, 60, 200, 175);
    }

    @Override
    public void update() {}

    @Override
    public void render(GameGraphics g) {
        testButton.render(g);
    }
}