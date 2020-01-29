package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Stroke;

public class GameGraphics {
    private Graphics2D g;

    public GameGraphics(Graphics2D g) {
        this.g = g;
    }

    public boolean drawImage(Image image, double x, double y, ImageObserver o) {
        return g.drawImage(image, (int) (x * Resources.scaleX), (int) (y * Resources.scaleY), o);
    }

    public boolean drawImage(Image image, double x, double y) {
        return drawImage(image, x, y, Game.getWindow());
    }

    public void setFont(Font font) {
        g.setFont(font.deriveFont((float) (font.getSize2D() * Resources.scaleX)));
    }

    public void setStroke(Stroke stroke) {
        g.setStroke(stroke);
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void setClip(Rectangle r) {
        Rectangle scaledRect = new Rectangle((int) (r.getX() * Resources.scaleX), (int) (r.getY() * Resources.scaleY),
                (int) (r.getWidth() * Resources.scaleX), (int) (r.getHeight() * Resources.scaleY));
        g.setClip(scaledRect);
    }

    public void clearClip() {
        g.setClip(0, 0, Game.getWindow().getWidth(), Game.getWindow().getHeight());
    }

    public Rectangle getClip() {
        return (Rectangle) g.getClip();
    }

    public void drawString(String text, double x, double y) {
        g.drawString(text, (int) (x * Resources.scaleX), (int) (y * Resources.scaleY));
    }

    public void fillRect(double x, double y, double width, double height) {
        g.fillRect((int) (x * Resources.scaleX), (int) (y * Resources.scaleY), (int) (width * Resources.scaleX), (int) (height * Resources.scaleY));
    }

    public void drawRect(double x, double y, double width, double height) {
        g.drawRect((int) (x * Resources.scaleX), (int) (y * Resources.scaleY), (int) (width * Resources.scaleX),
                (int) (height * Resources.scaleY));
    }
    public void drawLine(double x1, double y1, double x2, double y2) {
        g.drawLine((int) (x1 * Resources.scaleX), (int) (y1 * Resources.scaleY), (int) (x2 * Resources.scaleX), (int) (y2 * Resources.scaleY));
    }
}