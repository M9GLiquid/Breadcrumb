package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.core.Game;

import javax.swing.*;

public abstract class View extends JPanel {
    protected final GameFrame gameFrame;

    protected final Game game;

    public View(Game game){
        this.game = game;
        this.gameFrame = game.getGameFrame();
    }
}
