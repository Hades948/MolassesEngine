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
        boolean result =  g.drawImage(page.getImage(), (int) (x + camX), (int) (y + camY), o);
        return result;
    }

    public boolean drawPage(Page page, double x, double y) {
        return drawPage(page, x, y, Game.getWindow());
    }

    public void setFont(Font font) {
        g.setFont(font.deriveFont(font.getSize2D()));
    }

    public void setStroke(Stroke stroke) {
        g.setStroke(stroke);
    }

    public void setColor(Color color) {
        g.setColor(color);
    }

    public void setClip(Rectangle r) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        Rectangle adjustedRect = new Rectangle((int) (r.getX() + camX), (int) (r.getY() + camY), (int) r.getWidth(), (int) r.getHeight());
        g.setClip(adjustedRect);
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
        g.drawString(text, (int) (x + camX), (int) (y + camY));
    }

    public void fillRect(double x, double y, double width, double height) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.fillRect((int) (x + camX), (int) (y + camY), (int) width, (int) height);
    }

    public void drawRect(double x, double y, double width, double height) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawRect((int) (x + camX), (int) (y + camY), (int) width, (int) height);
    }
    public void drawLine(double x1, double y1, double x2, double y2) {
        double camX = camera == null ? 0 : camera.getOffsetX();
        double camY = camera == null ? 0 : camera.getOffsetY();
        g.drawLine((int) (x1 + camX), (int) (y1 + camY), (int) x2, (int) y2);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}