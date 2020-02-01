package com.tylerroyer.molasses;

import java.util.ArrayList;

public class TileMap {
    private ArrayList<ArrayList<Tile>> tiles;

    public TileMap(int width, int height) {
        tiles = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            tiles.add(new ArrayList<Tile>());
            for (int j = 0; j < width; j++) {
                tiles.get(i).add(new Tile());
            }
        }
    }

    public void fillTileMap(String imageName) {
        for (ArrayList<Tile> tileList : tiles) {
            for (Tile tile : tileList) {
                tile.setImageName(imageName);
            }
        }
    }

    public Tile getTile(int tileX, int tileY) {
        return tiles.get(tileY).get(tileX);
    }

    public void setTile(int tileX, int tileY, String imageName) {
        getTile(tileX, tileY).setImageName(imageName);
    }

    public int getWidth() {
        return tiles.get(0).size();
    }

    public int getHeight() {
        return tiles.size();
    }
}