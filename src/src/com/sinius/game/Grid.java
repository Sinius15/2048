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

    /**
     * @param vector the changes in x and y where to move to.
     * @return boolean weather or not the game is finished.
     */
    public boolean doMove(Point vector) {
        resetMerges();
        boolean isMoved;
        boolean nothingMoved = true;
        do {
            isMoved = false;
            for (int x = 0; x < Grid.SIZE; x++) {
                for (int y = 0; y < Grid.SIZE; y++) {
                    Point oldLocation = new Point(x, y);
                    Point newLocation = new Point(oldLocation);
                    newLocation.translate(vector.x, vector.y);

                    //the target point is outside of the bounds so we can not move this tile.
                    if (!Grid.isPointValid(newLocation))
                        continue;

                    Tile oldTile = getTile(oldLocation);
                    Tile newTile = getTile(newLocation);

                    //the current tile has no number so do not do anything
                    if(oldTile.getAmount() == 0)
                        continue;

                    //target location is the same as the current location so lets merge
                    if(newTile.getAmount() == oldTile.getAmount() && !newTile.wasMerged() && !oldTile.wasMerged()){
                        newTile.setAmount(oldTile.getAmount()*2);
                        oldTile.setAmount(0);
                        newTile.setWasMerged(true);
                        isMoved = true;
                        nothingMoved = false;
                        continue;
                    }

                    //the target location is empty so lets go there!
                    if (getTile(newLocation).getAmount() == 0) {
                        turn(newLocation, oldLocation);
                        isMoved = true;
                        nothingMoved = false;
                    }
                }
            }
        }while(isMoved);

        if(!nothingMoved){
            if(!addRandomTile()){
                //game finised!
                return true;
            }
        }
        return false;
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
