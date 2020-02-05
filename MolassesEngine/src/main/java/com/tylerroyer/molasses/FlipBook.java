package com.tylerroyer.molasses;

import com.tylerroyer.molasses.events.Event;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FlipBook {
    private enum FlipBookType {SPRING, CYCLIC, RANDOM};
    private FlipBookType flipBookType;
    private boolean started = false;
    private long timeOfStart;
    private long flipDelay;
    private int pageIndex;
    private List<Page> pages;
    private List<Event> events;
    
    public FlipBook(String flipBookFileName) {
        try (Scanner scanner = new Scanner(new FileInputStream(new File(Config.projectResourcePath + flipBookFileName)))) {
            flipDelay = scanner.nextLong();
            flipBookType = FlipBookType.valueOf(scanner.nextLine().toUpperCase());
            while (scanner.hasNextLine()) {
                pages.add(new Page(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {e.printStackTrace();}
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void update() {
        if (!started) {
            started = true;
            timeOfStart = System.currentTimeMillis();
        }

        // Alter pageIndex if needed.
        switch (flipBookType) {
            default:
            case SPRING:
                // TODO
                break;
            case CYCLIC:
                long range = flipDelay * pages.size();
                long timeSinceStart = System.currentTimeMillis() - timeOfStart;
                pageIndex = (int) (timeSinceStart % range / pages.size());
                break;
            case RANDOM:
                // TODO
                break;
        }

        // Trigger events if needed.
        // TODO This needs testing.  These will get triggered more than once if the animation continues.
        if (pageIndex == pages.size() - 1) {
            for (Event e : events) {
                e.doAction();
            }
        }
    }

    public Page getCurrentPage() {
        return pages.get(pageIndex);
    }
}