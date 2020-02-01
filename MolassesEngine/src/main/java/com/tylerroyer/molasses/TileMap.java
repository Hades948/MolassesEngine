package com.tylerroyer.molasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TileMap {
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    private ArrayList<String> tileNames = new ArrayList<>();

    public TileMap(int width, int height) {
        for (int i = 0; i < height; i++) {
            tiles.add(new ArrayList<Tile>());
            for (int j = 0; j < width; j++) {
                tiles.get(i).add(new Tile());
            }
        }
    }

    public TileMap(String tileMapResourceName) {
        try (Scanner scanner = new Scanner(new FileInputStream(new File(Config.projectResourcePath + tileMapResourceName)))) {
            String tileName = scanner.nextLine();
            while (!tileName.equals(";")) {
                tileNames.add(tileName);
                tileName = scanner.nextLine();
            }
            int width = Integer.parseInt(scanner.nextLine());
            int height = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < height; i++) {
                tiles.add(new ArrayList<Tile>());
                for (int j = 0; j < width; j++) {
                    tiles.get(i).add(new Tile());
                }
            }

            fillTileMap(scanner.nextLine());

            while (scanner.hasNextLine()) {
                String name = scanner.next();
                int x = scanner.nextInt();
                int y = scanner.nextInt();

                setTile(x, y, name);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public ArrayList<String> getTileNames() {
        return tileNames;
    }
}