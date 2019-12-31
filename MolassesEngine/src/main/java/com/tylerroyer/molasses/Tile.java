package com.tylerroyer.molasses;

public class Tile {
    boolean collisionFlag;
    String imageName;

    public Tile() {
        collisionFlag = false;
        imageName = "";
    }

    public Tile(boolean collisionFlag, String imageName) {
        this.collisionFlag = collisionFlag;
        this.imageName = imageName;
    }

    public boolean collisionFlag() {
        return collisionFlag;
    }

    public void setCollisionFlag(boolean collisionFlag) {
        this.collisionFlag = collisionFlag;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}