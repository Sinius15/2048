package com.sinius.game;

/**
 * Created by Sinius on 16-6-2015.
 */
public enum Move {

    RIGHT(1, 0),
    LEFT(-1, 0),
    DOWN(0, 1),
    UP(0, -1);

    private int x, y;

    Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
