package com.tylerroyer.molasses;

import java.awt.Color;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Provides basic window for the game.
 */
public class Window extends JFrame {
    private static final long serialVersionUID = 4159603562974028158L;

    Window(String title) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        getContentPane().add(Game.getLooper(), BorderLayout.CENTER);
        pack();
        centerOnScreen();
        setVisible(true);
    }

    public void setBackgroundColor(Color color) {
        Game.getLooper().setBackground(color);
    }

    private void centerOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowX, windowY;

        windowX = (int) (screenSize.getWidth() / 2 - getActualScaledWidth() / 2);
        windowY = (int) (screenSize.getHeight() / 2 - getActualScaledHeight() / 2);

        setLocation(new Point(windowX, windowY));
    }

    @Override
    public int getWidth() {
        return (int) (super.getWidth() / Game.scaleX);
    }

    public int getActualScaledWidth() {
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) (super.getHeight() / Game.scaleY);
    }

    public int getActualScaledHeight() {
        return super.getHeight();
    }
}
