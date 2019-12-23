package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;

import java.awt.Color;
import java.awt.Graphics2D;

class TestScreen extends Screen {
    public TestScreen() {}

    @Override
    public void loadResources() {}

    @Override
    public void update() {}

    @Override
    public void render(Graphics2D g) {
        Game.setBackgroundColor(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("Test", 200, 200);
    }
}