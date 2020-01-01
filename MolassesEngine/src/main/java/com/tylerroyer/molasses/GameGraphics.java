package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;

public class GameGraphics {
    private Graphics2D g;

    public GameGraphics(Graphics2D g) {
        this.g = g;
    }

    public boolean drawImage(Image image, int x, int y, ImageObserver o) {
        return g.drawImage(image, (int) (x * Resources.scaleX), (int) (y * Resources.scaleY), o);
    }

    public boolean drawImage(Image image, int x, int y) {
        return drawImage(image, x, y, Game.getWindow());
    }

    public void setFont(Font font) {
        g.setFont(font.deriveFont((float) (font.getSize2D() * Resources.scaleX)));
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void setClip(Rectangle r) {
        Rectangle scaledRect = new Rectangle((int) (r.getX() * Resources.scaleX), (int) (r.getY() * Resources.scaleX),
                (int) (r.getWidth() * Resources.scaleX), (int) (r.getHeight() * Resources.scaleX));
        g.setClip(scaledRect);
    }

    public Rectangle getClip() {
        return (Rectangle) g.getClip();
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

    public void drawLine(int x1, int y1, int x2, int y2) {
        g.drawLine((int) (x1 * Resources.scaleX), (int) (y1 * Resources.scaleY), (int) (x2 * Resources.scaleX), (int) (y2 * Resources.scaleY));
    }
}