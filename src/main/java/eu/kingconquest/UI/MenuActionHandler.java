package eu.kingconquest.UI;

import eu.kingconquest.Core.Sokoban;

import javax.swing.*;
import java.awt.*;

public class MenuActionHandler implements MenuObserver {
    private final Sokoban game;
    private final JFrame frame;

    public MenuActionHandler(Sokoban game, JFrame frame) {
        this.frame = frame;
        this.game = game;

    }

    @Override
    public void onMenuAction(String action) {
        switch (action) {
            case "start" -> {
                game.start();
                frame.getContentPane().removeAll();
                frame.add(game.gamePanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                game.gamePanel.requestFocusInWindow();
            }
            case "exit" -> System.exit(0);
            case "resume" -> {} // Nothing Happens
            case "restart" -> game.reset();
        }
    }
}
