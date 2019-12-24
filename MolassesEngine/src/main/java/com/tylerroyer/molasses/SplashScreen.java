// This class features resource scaling code found here: https://blog.idrsolutions.com/2019/05/image-scaling-in-java/
package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * A basic splash screen to play at the launch of the game.
 */
class SplashScreen extends Screen {
    private int counter = 0;
    private Screen firstScreen;
    private BufferedImage splash;

    /**
     * @param firstScreen The screen to switch to after the splash screen has finished.
     */
    public SplashScreen(Screen firstScreen) {
        this.firstScreen = firstScreen;
    }

    @Override
    public void loadResources() {
        splash = Resources.loadEngineGraphicalImage("engine_splash.png");
    }

    @Override
    public void update() {
        counter++;

        if (counter >= 2*60) {
            Game.setCurrentScreen(firstScreen);
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
}