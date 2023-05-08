package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.sokoban.Listener.MenuItem;
import eu.kingconquest.sokoban.Listener.MenuListener;

import java.awt.*;

public class StartMenu extends Menu {
    private final Game game;

    public StartMenu(Game game) {
        this.game = game;
        createMenu();
    }

    @Override
    public void createMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        add(textComponent(game.getTitle(), 24, false), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(textComponent("may the odds be ever in your favor", 24, true), gbc);

        // Button for load game
        gbc.gridy++;
        add(buttonComponent("Start Game",
                new MenuListener(game, this, MenuItem.START)),
                gbc);

        // Button for load game
        gbc.gridy++;
        add(buttonComponent("Load Game",
                new MenuListener(game, this, MenuItem.LOAD)),
                gbc);

        // Button for return to Game Selector
        gbc.gridy++;
        add(buttonComponent("Game Selector",
                new MenuListener(game, this, MenuItem.PLATFORM)),
                gbc);

        // Button to exit the game
        gbc.gridy++;
        add(buttonComponent("Exit Game",
                new MenuListener(game, this, MenuItem.EXIT)),
                gbc);
    }
}