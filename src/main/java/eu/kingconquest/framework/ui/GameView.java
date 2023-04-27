package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.Game;
import eu.kingconquest.framework.GameBoard;
import eu.kingconquest.framework.entity.Entity;

import java.awt.*;

/**
 * The GameView class is a JPanel by extension of the View class that displays the game's main graphics.
 */
public class GameView extends View {

    /**
     * Constructs a GameView for the specified game. This panel is responsible
     * for drawing the playing field and managing the entities.
     *
     * @param game       the game to create the GamePanel for
     */
    public GameView(Game game) {
        super(game);
    }

    /**
     * Overrides the paintComponent method of JPanel to draw the game's graphics.
     *
     * @param g the Graphics context to draw the game components on
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        GameBoard board = game.getBoard();

        // Draw the playing field
        for (int row = 0; row < board.ROWS; row++)
            for (int col = 0; col < board.COLS; col++)
                board.grid[row][col].draw(g);

        // Draw the entities on the playing field
        for (Entity entity : game.getEntities())
            entity.draw(g);
    }
}
