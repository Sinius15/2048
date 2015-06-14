package com.sinius.game;

import javax.swing.JOptionPane;
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
        if(finished)
            return;
        Point vector = null;
        switch (keyCode){
            case 38: vector = new Point(0, -1); break;
            case 39: vector = new Point(1, 0); break;
            case 40: vector = new Point(0, 1); break;
            case 37: vector = new Point(-1, 0); break;
        }
        if(vector == null)
            return;
        if(grid.doMove(vector)) {
            finished = true;
            JOptionPane.showMessageDialog(null, "Congratulations!");
        }
    }

    public static void main(String[] args){
        new Game();
    }
}
