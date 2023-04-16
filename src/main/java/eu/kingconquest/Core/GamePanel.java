package eu.kingconquest.Core;

import eu.kingconquest.Entities.Crate;
import eu.kingconquest.UI.StartMenu;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    public final int FRAME_WIDTH = 979;
    public final int FRAME_HEIGHT = 671;
    public JFrame frame;

    Sokoban game;

    public GamePanel(Sokoban game) {
        this.game = game;
        game.registerObserver(this);
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Sokoban Game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setFocusable(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);

        frame.add(new StartMenu(game, this), BorderLayout.CENTER);
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
        if (game.getPlayer() != null)
            game.getPlayer().draw(g);

        // Draw Crates
        if (!game.getCrates().isEmpty())
            for(Crate crate : game.getCrates())
                crate.draw(g);
    }

    @Override
    public void update(Sokoban game) {switch(game.getState()){
        case WIN -> game.start();
        case GAME_OVER -> game.gameOver();
        case INITIATING -> {
            game.initiate();
            game.start();
        }
        case RUNNING -> {} // Do nothing
        case RESETTING -> game.reset();
    }
    }


}
