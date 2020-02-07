package com.tylerroyer.molasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.commons.lang3.mutable.MutableInt;

public class TileMap implements GameObject {
    private double devScale;
    private ArrayList<ArrayList<Tile>> tiles = new ArrayList<>();
    private ArrayList<HashMap<String, FlipBook>> tileMappings = new ArrayList<>();
    private HashMap<String, Boolean> solidityMappings = new HashMap<>();
    private double x, y;
    private List<Double> zoomLevels = new ArrayList<>();
    private MutableInt zoomLevelIndex = new MutableInt(0);
    private int defaultZoomLevelIndex = 0;

    public TileMap(double x, double y, String tileMapResourceName) {
        this.x = x;
        this.y = y;

        try (Scanner scanner = new Scanner(new FileInputStream(new File(Config.projectResourcePath + tileMapResourceName)))) {
            String flipBookName = scanner.next();
            boolean isSolid = scanner.nextBoolean();
            scanner.nextLine();
            HashMap<String, FlipBook> map = new HashMap<>();
            while (!flipBookName.equals(";")) {
                FlipBook fb = new FlipBook(flipBookName);
                devScale = 128 / fb.getCurrentPage().getWidth();
                fb.scale(devScale, devScale);
                map.put(flipBookName, fb);
                solidityMappings.put(flipBookName, isSolid);

                flipBookName = scanner.next();
                if (!flipBookName.equals(";")) {
                    isSolid = scanner.nextBoolean();
                }
                scanner.nextLine();
            }
            tileMappings.add(map);
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

                getTile(tileX, tileY).setIsSolid(solidityMappings.get(name));
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

    public void prepareTileMapForScaling(MutableInt zoomLevelIndex, int defaultZoomLevelIndex, ArrayList<Double> zoomLevels) {
        if (!this.zoomLevels.isEmpty()) {
            System.err.println("Cannot prepare tile map for scaling more than once.");
        }

        this.zoomLevelIndex = zoomLevelIndex;
        this.defaultZoomLevelIndex = defaultZoomLevelIndex;
        this.zoomLevels = zoomLevels;

        ArrayList<HashMap<String, FlipBook>> newTileMappings = new ArrayList<>();
        for (Double zoomLevel : zoomLevels) {
            HashMap<String, FlipBook> newMap = new HashMap<>();
            for (HashMap<String, FlipBook> oldMap : tileMappings) {
                for (Entry<String, FlipBook> entry : oldMap.entrySet()) {
                    FlipBook fb = new FlipBook(entry.getValue());
                    fb.scale(zoomLevel, zoomLevel);
                    newMap.put(entry.getKey(), fb);
                }
            }
            newTileMappings.add(newMap);
        }

        tileMappings.clear();
        for (HashMap<String, FlipBook> map : newTileMappings) {
            tileMappings.add(map);
        }
    }

    public int getZoomLevelIndex() {
        return zoomLevelIndex.getValue();
    }

    public double getZoomLevel() {
        return zoomLevels.get(getZoomLevelIndex());
    }

    public int getNumberOfZoomLevels() {
        return zoomLevels.size();
    }

    public HashMap<String, FlipBook> getTileMappings() {
        return tileMappings.get(getZoomLevelIndex());
    }

    public HashMap<String, FlipBook> getTileMappingsWithDefaultZoom() {
        return tileMappings.get(defaultZoomLevelIndex);
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

    public double getTileSize() {
        for (Entry<String, FlipBook> entry : tileMappings.get(getZoomLevelIndex()).entrySet()) {
            return entry.getValue().getCurrentPage().getWidth() * getZoomLevel() * devScale;
        }

        return 0.0;
    }

    @Override
    public void update() {
        for (FlipBook fb : tileMappings.get(getZoomLevelIndex()).values()) {
            fb.update();
        }
        if (getZoomLevelIndex() != defaultZoomLevelIndex) {
            for (FlipBook fb : tileMappings.get(defaultZoomLevelIndex).values()) {
                fb.update();
            }
        }
    }

    @Override
    public void render(GameGraphics g) {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Page currentTilePage = tileMappings.get(getZoomLevelIndex()).get(getTile(i, j).getFlipBookName()).getCurrentPage();
                g.drawPage(currentTilePage, x + getTileSize() * i, y + getTileSize() * j);
                if (getTile(i, j).isSolid())
                    g.drawString("S", x + getTileSize() * i, y + getTileSize() * j);
            }
        }
    }

    // TODO This is only useful for TME.  Ideally, it wouldn't be necessary.
    public boolean isTileSolid(String name) {
        return solidityMappings.get(name);
    }
}