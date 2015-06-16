package com.sinius.game;

import java.awt.Point;
import java.util.ArrayList;

import static com.sinius.game.Move.DOWN;

/**
 * Created by Sinius on 10-6-2015.
 */
public class Grid implements Cloneable{

    public static final int SIZE = 4;
    Tile[][] tiles = new Tile[SIZE][SIZE];
    private int merges;

    public Grid() {
        for(int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                setTile(x, y, new Tile());
            }
        }
    }

    public Grid(Tile[][] tiles){
        this.tiles = tiles;
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
     * @param move the changes in x and y where to move to.
     * @return boolean weather or not the game is finished.
     */
    public boolean doMove(Move move) {
        resetMerges();
        boolean isMoved;
        boolean nothingMoved = true;

        do {
            isMoved = false;
            if(move == Move.UP || move == Move.LEFT)
                for (int x = 0; x < Grid.SIZE; x++) {
                    for (int y = 0; y < Grid.SIZE; y++) {
                        if(doNextMove(move ,x, y)){
                            isMoved = true;
                            nothingMoved = false;
                        }
                    }
                }
            else
                for (int x = Grid.SIZE-1; x >= 0 ; x--) {
                    for (int y = Grid.SIZE-1; y >= 0; y--) {
                        if(doNextMove(move ,x, y)){
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

    /**
     * Do the next move if possible
     * @param move The move to do
     * @param x The x location of the move
     * @param y The y location of the move
     * @return if or not there was movement
     */
    private boolean doNextMove(Move move, int x, int y){
        Point oldLocation = new Point(x, y);
        Point newLocation = new Point(oldLocation);
        newLocation.translate(move.getX(), move.getY());

        //the target point is outside of the bounds so we can not move this tile.
        if (!Grid.isPointValid(newLocation))
            return false;

        Tile oldTile = getTile(oldLocation);
        Tile newTile = getTile(newLocation);

        //the current tile has no number so do not do anything
        if(oldTile.getAmount() == 0)
            return false;

        //target location is the same as the current location so lets merge
        if(newTile.getAmount() == oldTile.getAmount() && !newTile.wasMerged() && !oldTile.wasMerged()){
            merges +=oldTile.getAmount();

            newTile.setAmount(oldTile.getAmount()*2);
            oldTile.setAmount(0);
            newTile.setWasMerged(true);
            return true;
        }

        //the target location is empty so lets go there!
        if (getTile(newLocation).getAmount() == 0) {
            turn(newLocation, oldLocation);
            return true;
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

    @Override
    protected Grid clone(){
        return new Grid(this.tiles);
    }

    public int getMerges() {
        return merges;
    }
}
