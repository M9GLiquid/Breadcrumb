package eu.kingconquest.framework.views;

import eu.kingconquest.framework.models.GameBoard;
import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.observers.GameViewObserver;
import eu.kingconquest.framework.utils.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * The GameView class is a JPanel by extension of the View class that displays the game's main graphics.
 */
public class GameGuiView extends JPanel implements GameViewObserver {
    private final GameBoard board;

    /**
     * Constructs a GameView for the specified game. This panel is responsible
     * for drawing the playing field and managing the entities.
     *
     * @param board       the game logic to create the GameView for
     */
    public GameGuiView(GameBoard board) {
        this.board = board;
    }

    /**
     * Overrides the paintComponent method of JPanel to draw the game's graphics.
     *
     * @param graphics the Graphics context to draw the game components on
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // Draw the playing field
        for (int row = 0; row < board.ROWS; row++)
            for (int col = 0; col < board.COLS; col++)
                board.grid[row][col].draw(graphics);

        // Draw the entities on the playing field
        for (Entity entity : board.getEntities())
            if (!(entity instanceof Tile))
                entity.draw(graphics);
    }

    @Override
    public void update() {
        repaint();
    }
}