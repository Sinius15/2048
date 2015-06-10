package com.sinius.game;

import java.awt.Point;

/**
 * Created by Sinius on 10-6-2015.
 */
public class Game {

    GameWindow window;
    Grid grid;
    boolean finished = false;

    public Game() {
        this.grid = new Grid();
        this.grid.addRandomTile();
        this.grid.addRandomTile();

        this.window = new GameWindow(this.grid, this);
    }

    public void onKeyPress(int keyCode) {
        Point vector = null;
        switch (keyCode){
            case 38: vector = new Point(0, -1); break;
            case 39: vector = new Point(1, 0); break;
            case 40: vector = new Point(0, 1); break;
            case 37: vector = new Point(-1, 0); break;
        }
        if(vector == null)
            return;

        doMove(vector);
    }

    private void doMove(Point vector) {
        if(finished)
            return;
        grid.resetMerges();
        boolean isMoved;
        do {
            isMoved = false;
            for (int x = 0; x < Grid.SIZE; x++) {
                for (int y = 0; y < Grid.SIZE; y++) {
                    Point oldLocation = new Point(x, y);
                    Point newLocation = new Point(oldLocation);
                    newLocation.translate(vector.x, vector.y);

                    if (!Grid.isPointValid(newLocation)) {
                        //the target point is outside of the bounds so we can not move this tile.
                        continue;
                    }

                    Tile oldTile = grid.getTile(oldLocation);
                    Tile newTile = grid.getTile(newLocation);


                    if(oldTile.getAmount() == 0) {
                        //the current tile has no number so do not do anything
                        continue;
                    }
                    if(newTile.getAmount() == oldTile.getAmount() && !newTile.wasMerged() && !oldTile.wasMerged()){
                        //merge!
                        newTile.setAmount(oldTile.getAmount()*2);
                        oldTile.setAmount(0);
                        newTile.setWasMerged(true);
                        isMoved = true;
                        continue;
                    }
                    if (grid.getTile(newLocation).getAmount() == 0) {
                        //the target location is empty so lets go there!
                        grid.turn(newLocation, oldLocation);
                        isMoved = true;
                        continue;
                    }


                }
            }
        }while(isMoved);
        if(!grid.addRandomTile()){
            //game finised!
            this.finished = true;
        }
    }


    public static void main(String[] args){
        new Game();
    }
}
