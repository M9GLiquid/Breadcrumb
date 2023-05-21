package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.Listener.MenuListener;
import eu.kingconquest.framework.core.Game;

import java.awt.*;

/**
 * PauseMenu class extends the abstract Menu class, representing the pause menu in the game UI.
 * This menu appears when the game is paused, and it allows players to resume, restart, save, load,
 * return to the main menu, or exit the game.
 */
public class PauseMenu extends Menu {
    private final Game game;

    /**
     * Constructs a PauseMenu with the provided game instance.
     *
     * @param game the current game instance
     */
    public PauseMenu(Game game){
        this.game = game;
        createMenu();
    }

    /**
     * Overridden from abstract Menu class.
     * Creates a pause menu with several options: Resume, Restart, Main Menu, Save, Load, and Exit.
     */
    @Override
    protected void createMenu() {
        // Setup Layout
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //TEXT
        // Label to display text
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent("Pause Menu", 48, false), gbc);

        // BUTTONS
        // Button to resume the game
        gbc.gridy++;
        add(buttonComponent("Resume",
                new MenuListener(game, this), "Resume"), gbc);

        // Button to restart the game
        gbc.gridy++;
        add(buttonComponent("Restart",
                new MenuListener(game, this), "Restart"), gbc);

        // Button for return to Main Menu
        gbc.gridy++;
        add(buttonComponent("Main Menu",
                new MenuListener(game, this), "Main Menu"), gbc);

        // Button for save game
        gbc.gridy++;
        add(buttonComponent("Save",
                new MenuListener(game, this), "Save"), gbc);

        // Button for load game
        gbc.gridy++;
        add(buttonComponent("Load",
                new MenuListener(game, this), "Load"), gbc);

        // Button to exit the game
        gbc.gridy++;
        add(buttonComponent("Exit",
                new MenuListener(game, this), "Exit"), gbc);

    }
}