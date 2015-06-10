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

    public void setTile(int x, int y, Tile tile){
        this.tiles[x][y] = tile;
    }

    public Tile getRandomAvalableTile(){
        ArrayList<Tile> avalable = getAvailableCells();
        if(avalable.size() == 0)
            return null;
        return avalable.get((int) Math.floor(Math.random()*avalable.size()));
    }

    public void addRandomTile(){
        int value = Math.random() < 0.9 ? 2 : 4;
        getRandomAvalableTile().setAmount(value);
    }

    private ArrayList<Tile> getAvailableCells() {
        ArrayList<Tile> avalable = new ArrayList<>();
        for(int x = 0; x < SIZE; x++){
            for(int y = 0; y < SIZE; y++){
                Tile tile = getTile(x, y);
                if(tile.getAmount() == 0)
                    avalable.add(tile);
            }
        }
        return avalable;
    }


}
