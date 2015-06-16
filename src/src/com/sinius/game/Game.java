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
    Simulator simulator;

    public Game() {
        this.grid = new Grid();
        this.grid.addRandomTile();
        this.grid.addRandomTile();
        simulator = new Simulator();

        this.window = new GameWindow(this.grid, this);
    }

    public void onKeyPress(int keyCode) {
        if(finished)
            return;
        Move move = null;
        System.out.println(keyCode);
        switch (keyCode){
            case 38: move = Move.UP; break;
            case 39: move = Move.RIGHT; break;
            case 40: move = Move.DOWN; break;
            case 37: move = Move.LEFT; break;
            case 32: move = simulator.simulate(grid.clone());
        }
        if(move == null)
            return;
        if(grid.doMove(move)) {
            finished = true;
            JOptionPane.showMessageDialog(null, "Congratulations!");
        }
    }

    public static void main(String[] args){
        new Game();
    }
}
