package eu.kingconquest.twozerofoureight.ui;

import eu.kingconquest.framework.Listener.MenuListener;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.ui.Menu;


import java.awt.*;

/**
 * The GameOverScreen class represents the screen displayed when the player lose the game.
 * It extends the Menu class.
 */
public class GameOverScreen extends Menu {
    private final Game game;
    private final int score;

    /**
     * Constructs a new WinScreen object.
     * @param game The game instance.
     * @param score The player's score.
     */
    public GameOverScreen(Game game, int score) {
        this.game = game;
        this.score = score;
        createMenu();
    }

    /**
     * Creates the menu components and layout for the losing screen.
     */
    @Override
    protected void createMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent("Game Over", 48, false), gbc);

        //Scoreboard
        gbc.gridy++;
        add(textComponent("Score: " + score, 24, false), gbc);

        // Button to restart the current level
        gbc.gridy++;
        add(buttonComponent("Restart",
                        new MenuListener(game, this)),
                gbc);

        // Button to return to the platform menu
        gbc.gridy++;
        add(buttonComponent("Game Menu",
                        new MenuListener(game, this)),
                gbc);

        // Button to exit the game
        gbc.gridy++;
        add(buttonComponent("Exit",
                        new MenuListener(game, this)),
                gbc);
    }
}
