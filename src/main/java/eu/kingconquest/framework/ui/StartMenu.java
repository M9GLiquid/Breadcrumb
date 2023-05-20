package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.Listener.MenuListener;
import eu.kingconquest.framework.core.Game;

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


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent(game.getTitle(), 48, false), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(textComponent("may the odds be ever in your favor", 24, true), gbc);

        // Button for start game
        gbc.gridy++;
        add(buttonComponent("Start",
                new MenuListener(game, this)),
                gbc);

        // Button for load game
        gbc.gridy++;
        add(buttonComponent("Load",
                new MenuListener(game, this)),
                gbc);

        // Button for return to Game Selector (PlatformMenu)
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