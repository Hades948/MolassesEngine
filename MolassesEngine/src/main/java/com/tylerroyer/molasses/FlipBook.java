package com.tylerroyer.molasses;

import com.tylerroyer.molasses.events.Event;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FlipBook {
    private enum FlipBookType {SPRING, CYCLIC, RANDOM};
    private FlipBookType flipBookType = FlipBookType.CYCLIC;
    private boolean started = false;
    private long timeOfStart;
    private long flipDelay;
    private int pageIndex;
    private List<Page> pages;
    private List<Event> events;
    
    // Copy constructor.
    public FlipBook(FlipBook other) {
        this.flipBookType = other.flipBookType;
        this.flipDelay = other.flipDelay;
        this.pages = new ArrayList<>();
        for (Page p : other.pages) {
            this.pages.add(new Page(p));
        }
        this.events = other.events;
    }

    public FlipBook(String flipBookFileName) {
        this.pages = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(new File(Config.projectResourcePath + flipBookFileName)))) {
            flipDelay = Long.parseLong(scanner.nextLine());
            flipBookType = FlipBookType.valueOf(scanner.nextLine().toUpperCase());
            while (scanner.hasNextLine()) {
                pages.add(new Page(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {e.printStackTrace();}
    }

    public FlipBook(long flipDelay, Page... pages) {
        this.flipDelay = flipDelay;
        this.pages = Arrays.asList(pages);
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
        if (pages.size() == 1 || flipDelay < 1) {
            pageIndex = 0;
        } else {
            switch (flipBookType) {
                default:
                case SPRING:
                    // TODO
                    break;
                case CYCLIC:
                    long range = flipDelay * pages.size();
                    long timeSinceStart = System.currentTimeMillis() - timeOfStart;
                    pageIndex = (int) (timeSinceStart % range / flipDelay);
                    break;
                case RANDOM:
                    // TODO
                    break;
            }
        }

        // Trigger events if needed.
        // TODO This needs testing.  These will get triggered more than once if the animation continues.
        if (events != null && !events.isEmpty()) {
            if (pageIndex == pages.size() - 1) {
                for (Event e : events) {
                    e.trigger();
                }
            }
        }
    }

    public Page getCurrentPage() {
        if (pages.isEmpty()) return null;
        return pages.get(pageIndex);
    }

    public FlipBook getDarkerCopy() {
        FlipBook res = new FlipBook(this);
        for (int i = 0; i < res.pages.size(); i++) {
            res.pages.set(i, res.pages.get(i).getDarkerCopy());
        }

        return res;
    }

    public FlipBook getBrighterCopy() {
        FlipBook res = new FlipBook(this);
        for (int i = 0; i < res.pages.size(); i++) {
            res.pages.set(i, res.pages.get(i).getBrighterCopy());
        }

        return res;
    }

    public void scale(double scaleX, double scaleY) {
        for (Page p : pages) {
            p.scale(scaleX, scaleY);
        }
    }
}