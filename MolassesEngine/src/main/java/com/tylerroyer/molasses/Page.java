// This class features resource scaling code found here: https://blog.idrsolutions.com/2019/05/image-scaling-in-java/
package com.tylerroyer.molasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.io.*;

import javax.imageio.ImageIO;

public class Page {
    private BufferedImage image;
    private double scaleX = 1.0, scaleY = 1.0;

    // Copy constructor
    public Page(Page other) {
        this.image = Util.copyBufferedImage(other.image);
        this.scaleX = other.scaleX;
        this.scaleY = other.scaleX;
    }

    public Page(String name) {
        try {
            image = ImageIO.read(Page.class.getResourceAsStream(Config.pageResourcePath + name));
        } catch (IOException e) {e.printStackTrace();}
    }

    // Creates a page for use by the engine.
    Page(String name, boolean dummy) { // TODO I don't want this dummy variable here :/
        try {
            image = ImageIO.read(Page.class.getResourceAsStream(name));
        } catch (IOException e) {e.printStackTrace();}
    }

    public Page(BufferedImage image, double scaleX, double scaleY) {
        this.image = image;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Dimension getSize() {
        return new Dimension((int) getWidth(), (int) getHeight());
    }

    public double getWidth() {
        return image.getWidth() / scaleX;
    }

    public double getHeight() {
        return image.getHeight() / scaleY;
    }

    public Dimension getActualScaledSize() {
        return new Dimension((int) getActualScaledWidth(), (int) getActualScaledHeight());
    }

    public double getActualScaledWidth() {
        return image.getWidth();
    }

    public double getActualScaledHeight() {
        return image.getHeight();
    }

    public void scale(double scaleX, double scaleY) {
        this.scaleX *= scaleX;
        this.scaleY *= scaleY;

        BufferedImage scaledImage = new BufferedImage((int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY), BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = AffineTransform.getScaleInstance(scaleX, scaleY);
        final AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        scaledImage = ato.filter(image, scaledImage);
        
        this.image = scaledImage;
    }
    
    public Page getDarkerCopy() {
        BufferedImage res = Util.copyBufferedImage(image);

        for (int i = 0; i < res.getWidth(); i++) {
            for (int j = 0; j < res.getHeight(); j++) {
                int darkerColor = new Color(res.getRGB(i, j)).darker().getRGB();
                res.setRGB(i, j, darkerColor);
            }
        }

        return new Page(res, this.scaleX, this.scaleY);
    }

    public Page getBrighterCopy() {
        BufferedImage res = Util.copyBufferedImage(image);

        for (int i = 0; i < res.getWidth(); i++) {
            for (int j = 0; j < res.getHeight(); j++) {
                int brighterColor = new Color(res.getRGB(i, j)).brighter().getRGB();
                res.setRGB(i, j, brighterColor);
            }
        }

        return new Page(res, this.scaleX, this.scaleY);
    }
}