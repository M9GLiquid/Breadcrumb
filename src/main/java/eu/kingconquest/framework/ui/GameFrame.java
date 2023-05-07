package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.utils.Tile;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private View view;
    public GameFrame(Game game) {
        super(game.getTitle());
        setFocusable(true);
        setResizable(false);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        addKeyListener(game.getController());
    }



    /**
     * Adds a menu to the game frame.
     *
     * @param view the menu to be added
     */
    public void addView(View view, int width, int height) {
        changeSize(width, height);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        add(view, BorderLayout.CENTER);
        revalidate();
        repaint();
        requestFocusInWindow();
    }

    public void changeSize(int width, int height){
        setSize(getFrameWidth(width), getFrameHeight(height));
    }

    public int getFrameWidth(int cols) {
        return (cols * Tile.getTileSize());
    }

    public int getFrameHeight(int rows) {
        return (rows * Tile.getTileSize());
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}