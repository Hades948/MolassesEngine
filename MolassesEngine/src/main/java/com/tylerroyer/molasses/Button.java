package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class Button {
    BufferedImage pressed, unpressed, highlighted;
    int x, y;

    public Button(BufferedImage pressed, BufferedImage unpressed, BufferedImage highlighted, int x, int y) {
        this.pressed = pressed;
        this.unpressed = unpressed;
        this.highlighted = highlighted;
        this.x = x;
        this.y = y;
    }

    public Button(String text, Font font, Color pressedColor, Color unpressedColor, Color highlightedColor, Color textColor,
            int width, int height, int x, int y) {        
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
        
        this.x = x;
        this.y = y;
    }

    public Button(String text, Font font, Color backgroundColor, Color textColor, int width, int height, int x, int y) {        
        this(text, font, backgroundColor.darker(), backgroundColor, backgroundColor.brighter(), textColor, width, height, x, y);
    }

    private boolean isMouseHovering() {
        Point mouseOffset = new Point(x - Game.getMouseHandler().getX() + pressed.getWidth(), y - Game.getMouseHandler().getY() + pressed.getHeight());
        return pressed.getRaster().getBounds().contains(mouseOffset);
    }

    public boolean isDown() {
        return isMouseHovering() && Game.getMouseHandler().isDown();
    }

    public void render(Graphics2D g) {
        if (isDown())
            g.drawImage(pressed, x, y, Game.getWindow());
        else if (isMouseHovering())
            g.drawImage(highlighted, x, y, Game.getWindow());
        else
            g.drawImage(unpressed, x, y, Game.getWindow());
    }
}