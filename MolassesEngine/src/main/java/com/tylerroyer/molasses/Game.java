package com.tylerroyer.molasses;

import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * Static classs to hold all important instances for the game.
 */
public class Game {
    public static void start() {
        Resources.init();

        keyboardHandler = new KeyboardHandler();
        mouseHandler = new MouseHandler();
        looper = new Looper((int) (Config.windowWidth * Resources.scaleX), (int) (Config.windowHeight * Resources.scaleY));
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
     * The current screen of the game.  This is the game that will be updated and drawn in the Looper.
     */
    private static Screen currentScreen;
    public static Screen getCurrentScreen() {
        return currentScreen;
    }
    public static void setCurrentScreen(Screen screen) {
        // Unload previous resources.
        Resources.unloadAllResources();
        
        // Set next screen and load its resources.
        currentScreen = screen;
        screen.loadResources();
    }

    public static void close() {
        System.exit(0);
    }

    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(getWindow(), message);
    }
}
