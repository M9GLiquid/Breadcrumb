package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.Listener.MenuListener;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.ui.Menu;

import java.awt.*;

public class WinScreen extends Menu {

    private final Game game;

    public WinScreen(Game game){
        this.game = game;
        createMenu();
    }

    @Override
    protected void createMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent("You Won", 48, false), gbc);

        // Subtitle
        gbc.gridy++;
        add(textComponent("You completed the last level!", 24, false), gbc);

        // Button to return to the platform menu
        gbc.gridy++;
        add(buttonComponent("Main Menu",
                        new MenuListener(game, this)),
                gbc);

        // Button to return to the platform menu
        gbc.gridy++;
        add(buttonComponent("Game Selector",
                        new MenuListener(game, this)),
                gbc);

        // Button to exit the game
        gbc.gridy++;
        add(buttonComponent("Exit",
                        new MenuListener(game, this)),
                gbc);
    }
}
