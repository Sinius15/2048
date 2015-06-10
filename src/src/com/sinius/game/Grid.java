package com.sinius.game;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by Sinius on 10-6-2015.
 */
public class Grid {

    public static final int SIZE = 4;
    Tile[][] tiles = new Tile[SIZE][SIZE];

    public Grid() {
        for(int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                setTile(x, y, new Tile());
            }
        }
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public Tile getTile(Point p){
        return tiles[p.x][p.y];
    }

    public void resetMerges(){
        for(int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                getTile(x, y).setWasMerged(false);
            }
        }
    }

    public void setTile(int x, int y, Tile tile){
        this.tiles[x][y] = tile;
    }

    public Tile getRandomAvalableTile(){
        ArrayList<Tile> available = getAvailableCells();
        if(available.size() == 0)
            return null;
        return available.get((int) Math.floor(Math.random()*available.size()));
    }

    public boolean addRandomTile(){
        int value = Math.random() < 0.9 ? 2 : 4;
        Tile tile = getRandomAvalableTile();
        if(tile == null)
            return false;
        tile.setAmount(value);
        return true;
    }

    private ArrayList<Tile> getAvailableCells() {
        ArrayList<Tile> available = new ArrayList<>();
        for(int x = 0; x < SIZE; x++){
            for(int y = 0; y < SIZE; y++){
                Tile tile = getTile(x, y);
                if(tile.getAmount() == 0)
                    available.add(tile);
            }
        }
        return available;
    }


    public static boolean isPointValid(Point point) {
        return !(point.getX() < 0 || point.getX() >= SIZE || point.getY() < 0 || point.getY() >= SIZE);
    }

    public void turn(Point pointA, Point pointB) {
        Tile keeper = getTile(pointA);
        tiles[pointA.x][pointA.y] = getTile(pointB);
        tiles[pointB.x][pointB.y] = keeper;
    }
}
