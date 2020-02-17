package com.tylerroyer.molasses;

import com.tylerroyer.molasses.events.Event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 * Helper class for keyboard input.
 */
public class KeyboardHandler implements KeyListener {
    private HashSet<Integer> downKeys;
    private HashMap<Event, Integer> events = new HashMap<>();

    KeyboardHandler() {
        downKeys = new HashSet<>();
    }

    /**
     * @param keyCode The key to check the status of.
     * @return True if the given key is down.  False otherwise.
     */
    public boolean isKeyDown(int keyCode) {
        return downKeys.contains(keyCode);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        downKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Entry<Event, Integer> entry : events.entrySet()) {
            if (entry.getValue() == e.getKeyCode()) {
                entry.getKey().trigger();
            }
        }
        downKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void addEvent(int keyCode, Event e) {
        events.put(e, keyCode);
    }

    public void clearEvents() {
        events.clear();
    }
}