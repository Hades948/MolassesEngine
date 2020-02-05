package com.tylerroyer.molasses;

public class Tile {
    boolean collisionFlag;
    String flipBookName;

    public Tile() {
        collisionFlag = false;
        flipBookName = "";
    }

    public Tile(boolean collisionFlag, String flipBookName) {
        this.collisionFlag = collisionFlag;
        this.flipBookName = flipBookName;
    }

    public boolean collisionFlag() {
        return collisionFlag;
    }

    public void setCollisionFlag(boolean collisionFlag) {
        this.collisionFlag = collisionFlag;
    }

    public String getFlipBookName() {
        return flipBookName;
    }

    public void setFlipBookName(String flipBookName) {
        this.flipBookName = flipBookName;
    }
}