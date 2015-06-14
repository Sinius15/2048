package com.sinius.game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Sinius on 10-6-2015.
 */
public class Tile {

    private int amount = 0;
    private boolean wasMerged;

    public Tile() {
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

    public void draw(Rectangle location, Graphics g, int padding){
        int wp = padding+ location.width;
        int hp = padding+ location.height;

        String txt = getAmount()+"";

        g.setFont(new Font("Arial", Font.PLAIN, calcFontSize(txt)));

        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(txt, g2d);
        int x = (int) ((location.getWidth() - (int) r.getWidth()) / 2);
        int y = (int) ((location.getHeight() - (int) r.getHeight()) / 2 + fm.getAscent());

        g.setColor(getBackgroundColor());
        g.fillRoundRect(padding+location.x*wp, padding+location.y*hp, 100, 100, 10, 10);


        g.setColor(getForegroundColor());
        g.drawString(txt, padding+x+location.x*wp, padding+y+location.y*hp-3);
    }

    private static int calcFontSize(String txt){
        switch (txt.length()){
            case 1: return 60;
            case 2: return 50;
            case 3: return 40;
        }
        return 30;
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
