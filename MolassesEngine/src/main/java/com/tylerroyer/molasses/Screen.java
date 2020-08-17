package com.tylerroyer.molasses;

/**
 * Provides basic screen functionality for use with the Loooper.
 */
public abstract class Screen{
    
    /**
     * Loads all needed resources and variables for this screen.  This is done abstractly for each screen to
     * prevent overstacking the heap.
     */
    public abstract void onFocus();

    /**
     * Performs all needed updates for this screen.
     */
    public abstract void update();

    /**
     * Renders the screen to the provided graphics.
     * @param g The graphics to render to.  Generally provided by the looper.
     */
    public abstract void render(GameGraphics g);
}