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
    private Camera camera = null;

    public GameGraphics(Graphics2D g) {
        this.g = g;
    }

    public boolean drawImage(Image image, double x, double y, ImageObserver o) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        return g.drawImage(image, (int) (x * Resources.scaleX + camX), (int) (y * Resources.scaleY + camY), o);
    }

    public boolean drawImage(Image image, double x, double y) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        return drawImage(image, x + camX, y + camY, Game.getWindow());
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
        // TODO Should this be relative to the camera?
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
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawString(text, (int) (x * Resources.scaleX + camX), (int) (y * Resources.scaleY + camY));
    }

    public void fillRect(double x, double y, double width, double height) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.fillRect((int) (x * Resources.scaleX + camX), (int) (y * Resources.scaleY + camY), (int) (width * Resources.scaleX), (int) (height * Resources.scaleY));
    }

    public void drawRect(double x, double y, double width, double height) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawRect((int) (x * Resources.scaleX + camX), (int) (y * Resources.scaleY + camY), (int) (width * Resources.scaleX),
                (int) (height * Resources.scaleY));
    }
    public void drawLine(double x1, double y1, double x2, double y2) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawLine((int) (x1 * Resources.scaleX + camX), (int) (y1 * Resources.scaleY + camY), (int) (x2 * Resources.scaleX), (int) (y2 * Resources.scaleY));
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}