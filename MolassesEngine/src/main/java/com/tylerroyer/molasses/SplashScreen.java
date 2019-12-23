// This class features resource scaling code found here: https://blog.idrsolutions.com/2019/05/image-scaling-in-java/
package com.tylerroyer.molasses;

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
        BufferedImage unscaledSplash = Resources.loadEngineGraphicalImage("engine_splash.png");
        double scaleX = Game.getLooper().getBounds().getWidth() / unscaledSplash.getWidth();
        double scaleY = Game.getLooper().getBounds().getHeight() / unscaledSplash.getHeight();
        BufferedImage scaledSplash = new BufferedImage((int) (unscaledSplash.getWidth() * scaleX), (int) (unscaledSplash.getHeight() * scaleY), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledSplash = ato.filter(unscaledSplash, scaledSplash);
        splash = scaledSplash;
    }

    @Override
    public void update() {
        counter++;

        if (counter >= 2*60) {
            Game.setCurrentScreen(firstScreen);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(splash, 0, 0, Game.getWindow());
    }
}