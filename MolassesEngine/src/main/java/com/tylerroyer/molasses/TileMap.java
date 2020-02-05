package com.tylerroyer.molasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TileMap implements GameObject {
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    private HashMap<String, FlipBook> tileMappings = new HashMap<>();
    private double x, y;

    public TileMap(double x, double y, String tileMapResourceName) {
        this.x = x;
        this.y = y;

        try (Scanner scanner = new Scanner(new FileInputStream(new File(Config.projectResourcePath + tileMapResourceName)))) {
            String flipBookName = scanner.nextLine();
            while (!flipBookName.equals(";")) {
                tileMappings.put(flipBookName, new FlipBook(flipBookName));
                flipBookName = scanner.nextLine();
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
                int tileX = scanner.nextInt();
                int tileY = scanner.nextInt();

                setTile(tileX, tileY, name);
            }
        } catch (FileNotFoundException e) {e.printStackTrace();}
    }

    public void fillTileMap(String flipBookName) {
        for (ArrayList<Tile> tileList : tiles) {
            for (Tile tile : tileList) {
                tile.setFlipBookName(flipBookName);
            }
        }
    }

    public Tile getTile(int tileX, int tileY) {
        return tiles.get(tileY).get(tileX);
    }

    public void setTile(int tileX, int tileY, String flipBookName) {
        getTile(tileX, tileY).setFlipBookName(flipBookName);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    public int getWidth() {
        return tiles.get(0).size();
    }

    public int getHeight() {
        return tiles.size();
    }

    @Override
    public void update() {
        for (FlipBook fb : tileMappings.values()) {
            fb.update();
        }
    }

    @Override
    public void render(GameGraphics g) {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Page currentTilePage = tileMappings.get(tiles.get(i).get(j).getFlipBookName()).getCurrentPage();
                g.drawPage(currentTilePage, x + currentTilePage.getWidth() * i, currentTilePage.getHeight() * j);
            }
        }
    }
}