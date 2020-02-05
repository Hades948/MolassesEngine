package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Stroke;

public class GameGraphics {
    private Graphics2D g;
    private Camera camera = null;

    public GameGraphics(Graphics2D g) {
        this.g = g;
    }

    public boolean drawPage(Page page, double x, double y, ImageObserver o) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        boolean result =  g.drawImage(page.getImage(), (int) (x * Game.scaleX + camX), (int) (y * Game.scaleY + camY), o);
        return result;
    }

    public boolean drawPage(Page page, double x, double y) {
        return drawPage(page, x, y, Game.getWindow());
    }

    public void setFont(Font font) {
        g.setFont(font.deriveFont((float) (font.getSize2D() * Game.scaleX)));
    }

    public void setStroke(Stroke stroke) {
        g.setStroke(stroke);
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void setClip(Rectangle r) {
        // TODO Should this be relative to the camera?
        Rectangle scaledRect = new Rectangle((int) (r.getX() * Game.scaleX), (int) (r.getY() * Game.scaleY),
                (int) (r.getWidth() * Game.scaleX), (int) (r.getHeight() * Game.scaleY));
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
        g.drawString(text, (int) (x * Game.scaleX + camX), (int) (y * Game.scaleY + camY));
    }

    public void fillRect(double x, double y, double width, double height) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.fillRect((int) (x * Game.scaleX + camX), (int) (y * Game.scaleY + camY), (int) (width * Game.scaleX), (int) (height * Game.scaleY));
    }

    public void drawRect(double x, double y, double width, double height) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawRect((int) (x * Game.scaleX + camX), (int) (y * Game.scaleY + camY), (int) (width * Game.scaleX),
                (int) (height * Game.scaleY));
    }
    public void drawLine(double x1, double y1, double x2, double y2) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawLine((int) (x1 * Game.scaleX + camX), (int) (y1 * Game.scaleY + camY), (int) (x2 * Game.scaleX), (int) (y2 * Game.scaleY));
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}