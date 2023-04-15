package eu.kingconquest.Core;

import eu.kingconquest.Entities.Crate;
import eu.kingconquest.UI.MenuActionHandler;
import eu.kingconquest.UI.StartMenu;
import eu.kingconquest.api.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    public final int FRAME_WIDTH = 979;
    public final int FRAME_HEIGHT = 671;

    private MenuActionHandler menuActionHandler;

    Sokoban game;

    public GamePanel(Sokoban game) {
        this.game = game;
        game.registerObserver(this);
        initializeUI();
    }

    private void initializeUI() {
        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setFocusable(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        menuActionHandler = new MenuActionHandler(game, frame);

        StartMenu startMenu = new StartMenu();
        startMenu.registerObserver(menuActionHandler);
        frame.add(startMenu.createMenu(game), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SokobanBoard board = game.getBoard();

        // Draw the dungeon
        for(int row = 0; row < board.numRows; row++)
            for(int col = 0; col < board.numCols; col++)
                board.grid[row][col].draw(g);

        // Draw the player
        if (game.player != null)
            game.player.draw(g);

        // Draw Crates
        if (!game.crates.isEmpty())
            for(Crate crate : game.crates)
                crate.draw(g);
    }

    @Override
    public void update(Game game) {
        // Update the UI based on the new game state
    }


}
