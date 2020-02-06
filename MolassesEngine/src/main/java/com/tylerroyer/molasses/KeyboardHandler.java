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
    private HashMap<Integer, Event> events = new HashMap<>();

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
        for (Entry<Integer, Event> entry : events.entrySet()) {
            if (entry.getKey() == e.getKeyCode()) {
                entry.getValue().doAction();
            }
        }
        downKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void addEvent(int keyCode, Event e) {
        events.put(keyCode, e);
    }

    public void clearEvents() {
        events.clear();
    }
}