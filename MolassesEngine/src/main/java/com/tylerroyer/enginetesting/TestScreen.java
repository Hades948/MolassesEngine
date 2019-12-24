package com.tylerroyer.enginetesting;

import com.tylerroyer.molasses.*;
import com.tylerroyer.molasses.particles.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

class TestScreen extends Screen {
    Button colorButton, imageButton;
    Timer timer;
    ParticleEmitter colorPE, imagePE;

    public TestScreen() {
        timer = new Timer(60 * 1000);
    }

    @Override
    public void loadResources() {
        Font font = new Font("Helvetica", Font.PLAIN, 12);
        colorButton = new Button("Test", font, Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, 50, 50, 25, 10);

        BufferedImage pressed = Resources.loadGraphicalImage("pressed.png");
        BufferedImage unpressed = Resources.loadGraphicalImage("unpressed.png");
        BufferedImage highlighted = Resources.loadGraphicalImage("highlighted.png");
        imageButton = new Button(pressed, unpressed, highlighted, 130, 15);

        timer.start();

        colorPE = new ColorParticleEmitter(Color.BLUE, 350, 80, 10);

        BufferedImage particle = Resources.loadGraphicalImage("particle.png");
        imagePE = new ImageParticleEmitter(particle, 450, 80, 10);
    }

    @Override
    public void update() {
        timer.update();

        colorPE.update();

        imagePE.update();
    }

    @Override
    public void render(GameGraphics g) {
        Game.setBackgroundColor(Color.BLACK);
        g.setColor(Color.WHITE);
        for (int x = 0; x <= 1200; x += 100) {
            g.drawLine(x, 0, x, 800);
        }
        for (int y = 0; y <= 800; y += 100) {
            g.drawLine(0, y, 1200, y);
        }
        g.setFont(new Font("Helvetica", Font.PLAIN, 12));

        colorButton.render(g);
        g.drawString("color button", 5, 95);

        imageButton.render(g);
        g.drawString("image button", 105, 95);

        g.setFont(new Font("Helvetica", Font.PLAIN, 36));
        g.drawString((timer.getTimeLeftMillis() / 1000) + "", 229, 50);
        g.setFont(new Font("Helvetica", Font.PLAIN, 12));
        g.drawString("timer", 205, 95);

        colorPE.render(g);
        g.setColor(Color.WHITE);
        g.drawString("color pe", 305, 95);

        imagePE.render(g);
        g.drawString("image pe", 405, 95);
    }
}