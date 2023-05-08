package eu.kingconquest.sokoban.Listener;

import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.View;
import eu.kingconquest.sokoban.core.Sokoban;
import eu.kingconquest.twozerofoureight.core.Game2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlatformListener implements ActionListener {

    private final GameFrame gameFrame;
    private final View view;
    private final MenuItem menuItem;


    public PlatformListener(GameFrame gameFrame, View view, MenuItem menuItem) {
        this.gameFrame = gameFrame;
        this.menuItem = menuItem;
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (menuItem) {
            case SOKOBAN -> sokoban();
            case GAME2048 -> game2048();
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
