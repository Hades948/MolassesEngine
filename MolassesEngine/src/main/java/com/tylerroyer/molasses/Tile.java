package com.tylerroyer.molasses;

public class Tile {
    private boolean isSolid;
    private String flipBookName;

    public Tile() {
        isSolid = false;
        flipBookName = "";
    }

    public Tile(boolean isSolid, String flipBookName) {
        this.isSolid = isSolid;
        this.flipBookName = flipBookName;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setIsSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    public String getFlipBookName() {
        return flipBookName;
    }

    public void setFlipBookName(String flipBookName) {
        this.flipBookName = flipBookName;
    }
}