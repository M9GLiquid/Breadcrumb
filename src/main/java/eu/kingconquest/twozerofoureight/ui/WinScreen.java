package eu.kingconquest.twozerofoureight.ui;

import eu.kingconquest.framework.Listener.MenuListener;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.ui.Menu;

import java.awt.*;

/**
 * The WinScreen class represents the screen displayed when the player wins the game.
 * It extends the Menu class.
 */
public class WinScreen extends Menu {

    private final Game game;
    private final int score;

    /**
     * Constructs a new WinScreen object.
     * @param game The game instance.
     * @param score The player's score.
     */
    public WinScreen(Game game, int score){
        this.game = game;
        this.score = score;
        createMenu();
    }

    /**
     * Creates the menu components and layout for the win screen.
     */
    @Override
    protected void createMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent("You Won", 48, false), gbc);

        //Scoreboard
        gbc.gridy++;
        add(textComponent("Score: " + score, 24, false), gbc);

        // Button to resume the game if the player wants to continue.
        gbc.gridy++;
        add(buttonComponent("Resume",
                        new MenuListener(game, this), "Resume"),
                gbc);

        // Button to return to the main menu
        gbc.gridy++;
        add(buttonComponent("Main Menu",
                        new MenuListener(game, this), "Main Menu"),
                gbc);

        // Button to return to the platform menu
        gbc.gridy++;
        add(buttonComponent("Game Menu",
                        new MenuListener(game, this), "Game Menu"),
                gbc);

        // Button to exit the game
        gbc.gridy++;
        add(buttonComponent("Exit",
                        new MenuListener(game, this), "Exit"),
                gbc);
    }
}
