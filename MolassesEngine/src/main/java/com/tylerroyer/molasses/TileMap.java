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

    // TODO Ideally, tiles would be completely hiden, but I'm keeping it like this for development.
    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }
}