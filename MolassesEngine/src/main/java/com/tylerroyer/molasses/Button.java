package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.tylerroyer.molasses.events.Event;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

public class Button {
    FlipBook pressed, unpressed, highlighted;
    int x, y;
    ArrayList<Event> events = new ArrayList<>();
    Stroke outline = null;
    Color outlineColor = Color.BLACK;

    public Button(FlipBook unpressed, int x, int y, Event event) {
        this.x = x;
        this.y = y;
        this.events.add(event);
        this.unpressed = unpressed;
        this.pressed = unpressed.getDarkerCopy();
        this.highlighted = unpressed.getBrighterCopy();
    }

    public Button(FlipBook pressed, FlipBook unpressed, FlipBook highlighted, int x, int y, Event event) {
        this.pressed = pressed;
        this.unpressed = unpressed;
        this.highlighted = highlighted;
        this.x = x;
        this.y = y;
        this.events.add(event);
    }

    public Button(String text, Font font, Color pressedColor, Color unpressedColor, Color highlightedColor, Color textColor,
            int width, int height, int x, int y, Event event) {        
        BufferedImage pressedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage unpressedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage highlightedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D gPressed = pressedImage.createGraphics();
        gPressed.setColor(pressedColor);
        gPressed.fillRect(0, 0, width, height);
        gPressed.setFont(font);
        gPressed.setColor(textColor);
        // TODO Try to center the text on the button.  (POE Code?)
        gPressed.drawString(text, 10, height - 10);

        Graphics2D gUnpressed = unpressedImage.createGraphics();
        gUnpressed.setColor(unpressedColor);
        gUnpressed.fillRect(0, 0, width, height);
        gUnpressed.setFont(font);
        gUnpressed.setColor(textColor);
        gUnpressed.drawString(text, 10, height - 10);

        Graphics2D gHighlighted = highlightedImage.createGraphics();
        gHighlighted.setColor(highlightedColor.brighter());
        gHighlighted.fillRect(0, 0, width, height);
        gHighlighted.setFont(font);
        gHighlighted.setColor(textColor);
        gHighlighted.drawString(text, 10, height - 10);

        this.pressed = new FlipBook(0, new Page(pressedImage, 1.0, 1.0));
        this.unpressed = new FlipBook(0, new Page(unpressedImage, 1.0, 1.0));
        this.highlighted = new FlipBook(0, new Page(highlightedImage, 1.0, 1.0));
        
        this.x = x;
        this.y = y;

        this.events.add(event);
    }

    public Button(String text, Font font, Color backgroundColor, Color textColor, int width, int height, int x, int y, Event event) {        
        this(text, font, backgroundColor.darker(), backgroundColor, backgroundColor.brighter(), textColor, width, height, x, y, event);
    }

    private boolean isMouseHovering() {
        Page currentPage = unpressed.getCurrentPage();
        int mouseOffsetX = (int) (x - Game.getMouseHandler().getX() + currentPage.getActualScaledWidth());
        int mouseOffsetY = (int) (y - Game.getMouseHandler().getY() + currentPage.getActualScaledHeight());
        Point mouseOffset = new Point(mouseOffsetX, mouseOffsetY);
        Rectangle scaledBounds = new Rectangle((int) currentPage.getActualScaledWidth(), (int) currentPage.getActualScaledHeight());
        return scaledBounds.contains(mouseOffset);
    }

    // TODO I think this should just be inlined where needed.
    public boolean isDown() {
        return isMouseHovering() && Game.getMouseHandler().isDown();
    }

    public void setOutline(Stroke outline) {
        this.outline = outline;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public void render(GameGraphics g) {
        if (isDown())
            g.drawPage(pressed.getCurrentPage(), x, y, Game.getWindow());
        else if (isMouseHovering())
            g.drawPage(highlighted.getCurrentPage(), x, y, Game.getWindow());
        else
            g.drawPage(unpressed.getCurrentPage(), x, y, Game.getWindow());
        
        // Draw outline
        g.setColor(outlineColor);
        if (outline != null) {
            g.setStroke(outline);
            g.drawRect(x, y, unpressed.getCurrentPage().getActualScaledWidth(), unpressed.getCurrentPage().getActualScaledHeight());
        }
    }

    private boolean wasDown = false;
    public void update() {
        // Update flip books
        pressed.update();
        unpressed.update();
        highlighted.update();

        if (isMouseHovering() && !Game.getMouseHandler().isDown() && wasDown) {
            for (Event e : events) {
                e.doAction();
            }
        }

        if (isDown()) {
            wasDown = true;
        } else {
            wasDown = false;
        }
    }

    public void addEvent(Event event) {
        events.add(event);
    }
}