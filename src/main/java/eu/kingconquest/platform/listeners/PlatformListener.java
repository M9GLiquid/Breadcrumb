package eu.kingconquest.platform.listeners;

import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.Listener.MenuItem;
// The Different Games
import eu.kingconquest.sokoban.core.Sokoban;
import eu.kingconquest.twozerofoureight.core.Game2048;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlatformListener implements ActionListener {

    private final GameFrame gameFrame;
    private final JPanel view;
    private final MenuItem menuItem;


    public PlatformListener(GameFrame gameFrame, JPanel view, MenuItem menuItem) {
        this.gameFrame = gameFrame;
        this.menuItem = menuItem;
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (menuItem) {
            case GAME_SOKOBAN -> sokoban();
            case GAME_2048 -> game2048();
        }
    }

    private void sokoban() {
        new Sokoban(gameFrame);
        gameFrame.remove(view);
    }

    private void game2048() {
        new Game2048(gameFrame);
        gameFrame.remove(view);
    }

    protected void exit() {
        System.exit(0);
    }
}