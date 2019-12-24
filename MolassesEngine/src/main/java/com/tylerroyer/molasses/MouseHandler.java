package com.tylerroyer.molasses;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.MouseInfo;
import java.awt.Point;

/**
 * Helper class for mouse input.
 */
public class MouseHandler implements MouseListener {
    private boolean isDown = false;

    MouseHandler() {}

    /**
     * @return The mouse's X-coordinate relative to the window.
     */
    public int getX() {
        int absoluteX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int windowX = Game.getWindow().getX() + 8;
        return (int) ((absoluteX - windowX) * (1 / Resources.scaleX));
    }

    /**
     * @return The mouse's Y-coordinate relative to the window.
     */
    public int getY() {
        int absoluteY = (int) MouseInfo.getPointerInfo().getLocation().getY();
        int windowY = Game.getWindow().getY() + 31;
        return (int) ((absoluteY - windowY) * (1 / Resources.scaleY));
    }

    public Point getPoint() {
        return new Point(getX(), getY());
    }

    /**
     * @return True if m1 is down.  False otherwise.
     */
    public boolean isDown() {
        return isDown;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {isDown = true;}

    @Override
    public void mouseReleased(MouseEvent e) {isDown = false;}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}