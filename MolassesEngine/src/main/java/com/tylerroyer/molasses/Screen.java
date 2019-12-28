package com.tylerroyer.molasses;

import java.util.ArrayList;

/**
 * Provides basic screen functionality for use with the Loooper.
 */
public abstract class Screen implements ButtonListener{
    private ArrayList<Button> buttons = new ArrayList<>();

    /**
     * Loads all needed resources for this screen.  This is done abstractly for each screen to
     * prevent overstacking the heap.
     */
    public abstract void loadResources();

    /**
     * Performs all needed updates for this screen.
     */
    public abstract void update();

    /**
     * Renders the screen to the provided graphics.
     * @param g The graphics to render to.  Generally provided by the looper.
     */
    public abstract void render(GameGraphics g);

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void addButton(Button button) {
        buttons.add(button);
        button.addButtonListener(this);
    }
}