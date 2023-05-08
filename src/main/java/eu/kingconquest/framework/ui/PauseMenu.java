package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.sokoban.Listener.MenuItem;
import eu.kingconquest.sokoban.Listener.MenuListener;

import java.awt.*;

public class PauseMenu extends Menu {
    private final Game game;

    public PauseMenu(Game game){
        this.game = game;
        game.setState(GameState.PAUSED);
        createMenu();
    }

    @Override
    protected void createMenu() {
        // Setup Layout
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        // Label to display text
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent("Pause Menu", 48, false), gbc);

        // Button to resume the game
        gbc.gridy++;
        add(buttonComponent("Resume Game",
                new MenuListener(game, this, MenuItem.RESUME)), gbc);

        // Button for return to Main Menu
        gbc.gridy++;
        add(buttonComponent("Main Menu",
                new MenuListener(game, this, MenuItem.RETURN)), gbc);

        // Button for load game
        gbc.gridy++;
        add(buttonComponent("Load Game",
                new MenuListener(game, this, MenuItem.LOAD)), gbc);

        // Button for save game
        gbc.gridy++;
        add(buttonComponent("Save Game",
                new MenuListener(game, this, MenuItem.SAVE)), gbc);

        // Button for return to Game Selector
        gbc.gridy++;
        add(buttonComponent("Game Selector",
                new MenuListener(game, this, MenuItem.PLATFORM)), gbc);

        // Button to exit the game
        gbc.gridy++;
        add(buttonComponent("Exit Game",
                new MenuListener(game, this, MenuItem.EXIT)), gbc);

    }
}