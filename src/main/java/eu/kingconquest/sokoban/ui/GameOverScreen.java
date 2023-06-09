package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.Listener.MenuListener;
import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.ui.Menu;

import java.awt.*;

public class GameOverScreen extends Menu {
    private final Game game;

    public GameOverScreen(Game game) {
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
        add(textComponent("Game Over", 48, false), gbc);

        // Button to restart the current level
        gbc.gridy++;
        add(buttonComponent("Restart",
                        new MenuListener(game, this), "Restart"),
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
