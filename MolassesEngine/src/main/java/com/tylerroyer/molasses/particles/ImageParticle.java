package com.tylerroyer.molasses.particles;

import com.tylerroyer.molasses.*;

/**
 * Implements Particle using an image.
 */
class ImageParticle extends Particle {
    private Page page;

    public ImageParticle(Page page, int initialX, int initialY, float initialVelX, float initialVelY) {
        super(initialX, initialY, initialVelX, initialVelY);
        this.page = page; 
    }

    @Override
    void render(GameGraphics g) {
        g.drawPage(page, (int) x, (int) y, Game.getWindow());
    }
}