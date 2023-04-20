package eu.kingconquest.framework;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.ui.Menu;

import javax.swing.*;
import java.awt.*;

/**
 * The GamePanel class is a JPanel that displays the game's graphics and user interface.
 * It initializes and configures the game's UI components and handles adding menus and drawing entities.
 */
public class GamePanel extends JPanel {
    public JFrame frame;

    Game game;

    /**
     * Constructs a GamePanel for the specified game. This panel is responsible
     * for drawing the game board and managing the UI components associated with the game.
     * Note that this constructor specifically checks for a Sokoban game instance before initializing the UI.
     *
     * @param game the game to create the GamePanel for
     */
    public GamePanel(Game game) {
        this.game = game;
        game.gamePanel = this;
        initializeUI();
    }

    /**
     * Initializes and configures the game's UI components.
     */
    private void initializeUI() {
        frame = new JFrame("Sokoban Game");
        frame.setSize(game.getFrameWidth(), game.getFrameHeight());
        frame.setFocusable(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    /**
     * Adds a menu to the GamePanel.
     *
     * @param menu the menu to be added
     */
    public void addMenu(Menu menu) {
        frame.add(this, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        requestFocusInWindow();
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
        for (int row = 0; row < GameBoard.ROWS; row++)
            for (int col = 0; col < GameBoard.COLS; col++)
                board.grid[row][col].draw(g);

        // Draw the entities
        for (Entity entity : game.getEntities())
            entity.draw(g);
    }
}
