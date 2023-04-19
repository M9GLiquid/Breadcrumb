package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.Game;
import eu.kingconquest.framework.GamePanel;

import javax.swing.*;

public abstract class Menu extends JPanel {
    protected final GamePanel panel;

    protected final Game game;

    public Menu(Game game, GamePanel panel){
        this.game = game;
        this.panel = panel;
        createMenu();
    }

    protected abstract void createMenu();
}
