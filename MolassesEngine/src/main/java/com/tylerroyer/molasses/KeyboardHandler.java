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
    private HashMap<Integer, Event> downEvents = new HashMap<>();
    private HashMap<Integer, Event> typedEvents = new HashMap<>();

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
        for (Entry<Integer, Event> entry : downEvents.entrySet()) {
            if (entry.getKey() == e.getKeyCode()) {
                entry.getValue().doAction();
            }
        }
        downKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        downKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for (Entry<Integer, Event> entry : typedEvents.entrySet()) {
            if (entry.getKey() == e.getKeyCode()) {
                entry.getValue().doAction();
            }
        }
    }    

    public void addDownEvent(int keyCode, Event e) {
        downEvents.put(keyCode, e); // TODO Can I get rid of the weird type delay?
    }

    public void addTypedEvent(int keyCode, Event e) {
        typedEvents.put(keyCode, e);// TODO This doesn't work.
    }

    public void clearEvents() {
        downEvents.clear();
        typedEvents.clear();
    }
}