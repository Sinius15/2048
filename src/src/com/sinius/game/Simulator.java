package com.sinius.game;

/**
 * Created by Sinius on 16-6-2015.
 */
public class Simulator {

    /**
     * @param grid the grid to simulate on
     * @return Point vector the side it has to slide to
     */
    public Move simulate(Grid grid){
        Grid right = grid.clone();
        right.doMove(Move.RIGHT);
        Grid down = grid.clone();
        down.doMove(Move.DOWN);

        System.out.println(right.getMerges());
        System.out.println(down.getMerges());

        if(right.getMerges() > down.getMerges())
            return Move.RIGHT;
        if(down.getMerges() > right.getMerges())
            return Move.DOWN;
        return null;
    }

}
