package eu.kingconquest.UI;

import eu.kingconquest.Core.Game;
import eu.kingconquest.Core.GameObserver;
import eu.kingconquest.Core.Sokoban;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel implements GameObserver {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;

    private final MenuActionHandler menuActionHandler;

    Sokoban game;

    public GUI(Sokoban game) {
        this.game = game;
        game.registerObserver(this);
        menuActionHandler = new MenuActionHandler(game);
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setFocusable(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);

        StartMenu startMenu = new StartMenu();
        startMenu.registerObserver(menuActionHandler);
        frame.add(startMenu.createMenu(game), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the dungeon
    }

    @Override
    public void update(Game game) {
        // Update the UI based on the new game state
    }


}
