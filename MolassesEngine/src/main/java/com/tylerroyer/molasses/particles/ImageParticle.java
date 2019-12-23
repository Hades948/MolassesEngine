package com.tylerroyer.molasses.particles;

import com.tylerroyer.molasses.Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Implements Particle using an image.
 */
class ImageParticle extends Particle {
    private BufferedImage image;

    public ImageParticle(BufferedImage image, int initialX, int initialY, float initialVelX, float initialVelY) {
        super(initialX, initialY, initialVelX, initialVelY);
        this.image = image; 
    }

    @Override
    void render(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, Game.getWindow());
    }
}