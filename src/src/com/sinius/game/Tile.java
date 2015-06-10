package com.sinius.game;

import java.awt.Color;

/**
 * Created by Sinius on 10-6-2015.
 */
public class Tile {

    private int amount = 0;
    private boolean wasMerged;

    public Tile() {
    }

    public Tile(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Color getBackgroundColor() {
        return getBackground(this.amount);
    }

    public Color getForegroundColor() {
        return getForeground(this.amount);
    }

    public static Color getBackground(int amount) {
        switch (amount){
            case 0: return new Color(0xCCC0B3);
            case 2: return new Color(0xeee4da);
            case 4: return new Color(0xede0c8);
            case 8: return new Color(0xf2b179);
            case 16: return new Color(0xf59563);
            case 32: return new Color(0xf67c5f);
            case 64: return new Color(0xf65e3b);
            case 128: return new Color(0xedcf72);
            case 256: return new Color(0xedcc61);
            case 512: return new Color(0xedc850);
            case 1024: return new Color(0xedc53f);
            case 2048: return new Color(0xedc22e);
        }
        return new Color(0x3c3a32);
    }


    public static Color getForeground(int amount) {
        if(amount <= 4)
            return new Color(0x776e65);
        else
            return new Color(0xf9f6f2);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "amount=" + amount +
                '}';
    }

    public void setWasMerged(boolean wasMerged) {
        this.wasMerged = wasMerged;
    }

    public boolean wasMerged() {
        return wasMerged;
    }
}
