package com.tylerroyer.molasses;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Static classs to hold all important instances for the game.
 */
public class Game {
    public static void start() {
        mouseHandler = new MouseHandler();
        keyboardHandler = new KeyboardHandler();
        audioHandler = new AudioHandler();
        looper = new Looper();
        window = new Window(Config.windowTitle);
        setCurrentScreen(new SplashScreen());
    }

    /**
     * The window that the game is displayed in.
     */
    private static Window window;
    public static Window getWindow() {
        return window;
    }

    /**
     * The game's looper.
     */
    private static Looper looper;
    static Looper getLooper() {
        return looper;
    }
    public static void setBackgroundColor(Color color) {
        looper.setBackground(color);
    }

    /**
     * The game's mouse handler.
     */
    private static MouseHandler mouseHandler;
    public static MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    /**
     * The game's keyboard handler.
     */
    private static KeyboardHandler keyboardHandler;
    public static KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }

    /**
     * The game's audio handler.
     */
    private static AudioHandler audioHandler;
    public static AudioHandler getAudioHandler() {
        return audioHandler;
    }

    /**
     * The current screen of the game.  This is the game that will be updated and drawn in the Looper.
     */
    private static Screen currentScreen;
    public static Screen getCurrentScreen() {
        return currentScreen;
    }
    public static void setCurrentScreen(Screen screen) {
        keyboardHandler.clearEvents();
        currentScreen = screen;
        screen.onFocus();
    }

    public static void close() {
        System.exit(0);
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(getWindow(), message);
    }
}
