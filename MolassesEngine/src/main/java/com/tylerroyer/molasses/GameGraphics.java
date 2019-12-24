package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class GameGraphics {
    private Graphics2D g;

    public GameGraphics(Graphics2D g) {
        this.g = g;
    }

    public boolean drawImage(Image image, int x, int y, ImageObserver o) {
        return g.drawImage(image, (int) (x * Resources.scaleX), (int) (y * Resources.scaleY), o);
    }

    public void setFont(Font font) {
        g.setFont(font.deriveFont((float) (font.getSize2D() * Resources.scaleX)));
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void drawString(String text, int x, int y) {
        g.drawString(text, (int) (x * Resources.scaleX), (int) (y * Resources.scaleY));
    }

    public void fillRect(int x, int y, int width, int height) {
        g.fillRect((int) (x * Resources.scaleX), (int) (y * Resources.scaleY), (int) (width * Resources.scaleX),
                (int) (height * Resources.scaleY));
    }

    public void drawRect(int x, int y, int width, int height) {
        g.drawRect((int) (x * Resources.scaleX), (int) (y * Resources.scaleY), (int) (width * Resources.scaleX),
                (int) (height * Resources.scaleY));
    }
}