package com.tylerroyer.molasses;

import java.awt.Color;

/**
 * A basic splash screen to play at the launch of the game.
 */
class SplashScreen extends Screen {
    private int counter = 0;
    private Page splash;

    public SplashScreen() {}

    @Override
    public void onFocus() {
        splash = new Page("engine_splash.png", false);
    }

    @Override
    public void update() {
        counter++;

        if (counter >= 2*60) {
            Game.setCurrentScreen(Config.firstScreen);
        }
    }

    @Override
    public void render(GameGraphics g) {
        if (splash == null) return;

        Game.setBackgroundColor(new Color(50, 25, 0));

        int x = (int) (Game.getLooper().getWidth() / 2 - splash.getWidth() / 2);
        int y = (int) (Game.getLooper().getHeight() / 2 - splash.getHeight() / 2);
        g.drawPage(splash, x, y);
    }
}