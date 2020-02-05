package com.tylerroyer.molasses;

public interface GameObject {
    public double getX();

    public double getY();

    public void update();

    public void render(GameGraphics g);
}