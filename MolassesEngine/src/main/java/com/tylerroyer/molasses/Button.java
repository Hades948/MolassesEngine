package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;

import com.tylerroyer.molasses.events.Event;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Button {
    BufferedImage pressed, unpressed, highlighted;
    int x, y;
    Event event;

    public Button(BufferedImage pressed, BufferedImage unpressed, BufferedImage highlighted, int x, int y) {
        this.pressed = pressed;
        this.unpressed = unpressed;
        this.highlighted = highlighted;
        this.x = x;
        this.y = y;
    }

    public Button(String text, Font font, Color pressedColor, Color unpressedColor, Color highlightedColor, Color textColor,
            int width, int height, int x, int y, Event event) {        
        pressed = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        unpressed = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        highlighted = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D gPressed = pressed.createGraphics();
        gPressed.setColor(pressedColor);
        gPressed.fillRect(0, 0, width, height);
        gPressed.setFont(font);
        gPressed.setColor(textColor);
        // TODO Try to center the text on the button.  (POE Code?)
        gPressed.drawString(text, 10, height - 10);

        Graphics2D gUnpressed = unpressed.createGraphics();
        gUnpressed.setColor(unpressedColor);
        gUnpressed.fillRect(0, 0, width, height);
        gUnpressed.setFont(font);
        gUnpressed.setColor(textColor);
        gUnpressed.drawString(text, 10, height - 10);

        Graphics2D gHighlighted = highlighted.createGraphics();
        gHighlighted.setColor(highlightedColor.brighter());
        gHighlighted.fillRect(0, 0, width, height);
        gHighlighted.setFont(font);
        gHighlighted.setColor(textColor);
        gHighlighted.drawString(text, 10, height - 10);

        pressed = Resources.scaleImage(pressed);
        unpressed = Resources.scaleImage(unpressed);
        highlighted = Resources.scaleImage(highlighted);
        
        this.x = x;
        this.y = y;

        this.event = event;
    }

    public Button(String text, Font font, Color backgroundColor, Color textColor, int width, int height, int x, int y, Event event) {        
        this(text, font, backgroundColor.darker(), backgroundColor, backgroundColor.brighter(), textColor, width, height, x, y, event);
    }

    private boolean isMouseHovering() {
        // TODO This works.  But omg is it ugly.  Try to clean it up.  Try using x/y in onClick?
        int mouseOffsetX = (int) (x - Game.getMouseHandler().getX() + pressed.getWidth() * (1 / Resources.scaleX));
        int mouseOffsetY = (int) (y - Game.getMouseHandler().getY() + pressed.getHeight() * (1 / Resources.scaleY));
        Point mouseOffset = new Point(mouseOffsetX, mouseOffsetY);
        int width = (int) (pressed.getRaster().getWidth() * (1 / Resources.scaleX));
        int height = (int) (pressed.getRaster().getHeight() * (1 / Resources.scaleY));
        Rectangle scaledBounds = new Rectangle(width, height);
        return scaledBounds.contains(mouseOffset);
    }

    public boolean isDown() {
        return isMouseHovering() && Game.getMouseHandler().isDown();
    }

    public void render(GameGraphics g) {
        if (isDown())
            g.drawImage(pressed, x, y, Game.getWindow());
        else if (isMouseHovering())
            g.drawImage(highlighted, x, y, Game.getWindow());
        else
            g.drawImage(unpressed, x, y, Game.getWindow());
    }

    public void update() {
        if (isMouseHovering() && isDown()) {
            event.doAction();
        }
    }
}