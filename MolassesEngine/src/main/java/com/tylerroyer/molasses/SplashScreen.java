package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A basic splash screen to play at the launch of the game.
 */
class SplashScreen extends Screen {
    private int counter = 0;
    private BufferedImage splash;

    public SplashScreen() {}

    @Override
    public void loadResources() {
        splash = Resources.loadEngineGraphicalImage("engine_splash.png");
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

        int x = Game.getLooper().getWidth() / 2 - splash.getWidth() / 2;
        int y = Game.getLooper().getHeight() / 2 - splash.getHeight() / 2;
        g.drawImage(splash, (int) (x * (1 / Resources.scaleX)), (int) (y * (1 / Resources.scaleY)), Game.getWindow());
    }

    @Override
    public void onButtonClick(Button clickedButton) {
        // No buttons.
    }
}