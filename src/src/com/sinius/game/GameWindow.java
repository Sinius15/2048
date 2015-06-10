package com.sinius.game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Sinius on 10-6-2015.
 */
public class GameWindow extends JPanel implements Runnable, KeyListener {

    private JFrame frame;
    private Game game;
    private Grid grid;

    private Color background = new Color(0xBBADA0);

    public GameWindow(Grid grid, Game game) {
        super();
        frame = new JFrame("2048 challenge");
        frame.setResizable(false);
        frame.setSize(475, 500);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        this.setFocusable(true);
        this.addKeyListener(this);

        this.game = game;
        this.grid = grid;

        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));

        for(int x = 0; x < Grid.SIZE; x++) {
            for (int y = 0; y < Grid.SIZE; y++) {
                Tile tile = grid.getTile(x, y);
                g.setColor(tile.getBackgroundColor());
                g.fillRect(15+x*115, 15+y*115, 100, 100);

                g.setColor(tile.getForegroundColor());
                g.drawString(tile.getAmount()+"", 45+x*115, 85+y*115);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        game.onKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            this.repaint();
            try {
                Thread.sleep(1000/30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
