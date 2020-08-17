package com.tylerroyer.molasses.particles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Randomly emits a specified number of ColorParticles from one location. 
 */
public class ColorParticleEmitter extends ParticleEmitter {
    public ColorParticleEmitter(Color color, int x, int y, int count) {
        Random rand = new Random();

        particles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ColorParticle p = new ColorParticle(color, x, y, rand.nextFloat() * 4 - 2, rand.nextFloat() * 4 - 12);
            particles.add(p);
        }
    }
}