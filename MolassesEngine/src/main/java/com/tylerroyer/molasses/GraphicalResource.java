package com.tylerroyer.molasses;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public interface GraphicalResource {
    public BufferedImage getImage();
    
    public Dimension getSize();

    public double getWidth();

    public double getHeight();

    public Dimension getActualScaledSize();

    public double getActualScaledWidth();

    public double getActualScaledHeight();

    public void scaleResource(double scaleX, double scaleY);

    public GraphicalResource getDarkerCopy();

    public GraphicalResource getBrighterCopy();
}