package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

import com.tylerroyer.molasses.events.Event;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

public class Button {
    BufferedImage pressed, unpressed, highlighted;
    int x, y;
    ArrayList<Event> events = new ArrayList<>();
    Stroke outline = null;
    Color outlineColor = Color.BLACK;

    public Button(BufferedImage unpressed, int x, int y, Event event) {
        this.x = x;
        this.y = y;
        this.events.add(event);
        this.unpressed = unpressed;
        this.pressed = darker(unpressed);
        this.highlighted = brighter(unpressed);
    }

    private BufferedImage darker(BufferedImage src) {
        BufferedImage res = bufferedImageDeepCopy(src);

        for (int i = 0; i < res.getWidth(); i++) {
            for (int j = 0; j < res.getHeight(); j++) {
                int darkerColor = new Color(res.getRGB(i, j)).darker().getRGB();
                res.setRGB(i, j, darkerColor);
            }
        }

        return res;
    }

    private BufferedImage brighter(BufferedImage src) {
        BufferedImage res = bufferedImageDeepCopy(src);

        for (int i = 0; i < res.getWidth(); i++) {
            for (int j = 0; j < res.getHeight(); j++) {
                int lighterColor = new Color(res.getRGB(i, j)).brighter().getRGB();
                res.setRGB(i, j, lighterColor);
            }
        }

        return res;
    }

    private BufferedImage bufferedImageDeepCopy(BufferedImage src) {
        ColorModel colorModel = src.getColorModel();
        boolean isAlphaRemultiplied = colorModel.isAlphaPremultiplied();
        WritableRaster raster = src.copyData(null);
        return new BufferedImage(colorModel, raster, isAlphaRemultiplied, null);
    }

    public Button(BufferedImage pressed, BufferedImage unpressed, BufferedImage highlighted, int x, int y, Event event) {
        this.pressed = pressed;
        this.unpressed = unpressed;
        this.highlighted = highlighted;
        this.x = x;
        this.y = y;
        this.events.add(event);
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

        this.events.add(event);
    }

    public Button(String text, Font font, Color backgroundColor, Color textColor, int width, int height, int x, int y, Event event) {        
        this(text, font, backgroundColor.darker(), backgroundColor, backgroundColor.brighter(), textColor, width, height, x, y, event);
    }

    private boolean isMouseHovering() {
        int mouseOffsetX = (int) (x - Game.getMouseHandler().getX() + Resources.getResourceSize(pressed).getWidth());
        int mouseOffsetY = (int) (y - Game.getMouseHandler().getY() + Resources.getResourceSize(pressed).getHeight());
        Point mouseOffset = new Point(mouseOffsetX, mouseOffsetY);
        int width = (int) Resources.getResourceSize(pressed).getWidth();
        int height = (int) Resources.getResourceSize(pressed).getHeight();
        Rectangle scaledBounds = new Rectangle(width, height);
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
            g.drawImage(pressed, x, y, Game.getWindow());
        else if (isMouseHovering())
            g.drawImage(highlighted, x, y, Game.getWindow());
        else
            g.drawImage(unpressed, x, y, Game.getWindow());
        
        // Draw outline
        g.setColor(outlineColor);
        if (outline != null) {
            g.setStroke(outline);
            g.drawRect(x, y, Resources.getResourceSize(pressed).getWidth(), Resources.getResourceSize(pressed).getHeight());
        }
    }

    private boolean wasDown = false;
    public void update() {
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