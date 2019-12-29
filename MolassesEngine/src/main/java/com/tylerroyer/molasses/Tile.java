package com.tylerroyer.molasses;

import java.awt.image.BufferedImage;

public class Tile {
    boolean collisionFlag;
    BufferedImage image;

    public Tile() {
        collisionFlag = false;
        image = null;
    }

    public Tile(boolean collisionFlag, BufferedImage image) {
        this.collisionFlag = collisionFlag;
        this.image = image;
    }

    public boolean collisionFlag() {
        return collisionFlag;
    }

    public void setCollisionFlag(boolean collisionFlag) {
        this.collisionFlag = collisionFlag;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}