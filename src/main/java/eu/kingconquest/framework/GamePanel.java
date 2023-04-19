package eu.kingconquest.framework;

import eu.kingconquest.framework.entity.Entity;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.sokoban.core.Sokoban;
import eu.kingconquest.sokoban.core.SokobanBoard;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public JFrame frame;

    Game game;

    public GamePanel(Game game) {
        // Make sure we play the right game ;)
        if (!(game instanceof Sokoban)) {
            System.err.println("This is not Sokoban!");
            return;
        }
        this.game = game;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Sokoban Game");
        frame.setSize(game.getFrameWidth(), game.getFrameHeight());
        frame.setFocusable(true);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    public void addMenu(Menu menu) {
        frame.add(this, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
        requestFocusInWindow();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        SokobanBoard board = (SokobanBoard) game.getBoard();

        // Draw the playing field
        for (int row = 0; row < GameBoard.ROWS; row++)
            for (int col = 0; col < GameBoard.COLS; col++)
                board.grid[row][col].draw(g);

        // Draw the entities
        for (Entity entity : game.getEntities())
            entity.draw(g);
    }
}
