package com.sinius.game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Sinius on 10-6-2015.
 */
public class GameWindow extends JPanel implements Runnable, KeyListener, MouseListener {

    @SuppressWarnings("FieldCanBeLocal")
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
        this.addMouseListener(this);

        this.game = game;
        this.grid = grid;

        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());

        for(int x = 0; x < Grid.SIZE; x++) {
            for (int y = 0; y < Grid.SIZE; y++) {
                grid.getTile(x, y).draw(new Rectangle(x, y, 100, 100), g, 15);
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
    public void keyReleased(KeyEvent e) { }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getLocationOnScreen());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
