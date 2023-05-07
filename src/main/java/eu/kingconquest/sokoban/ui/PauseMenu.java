package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.core.GameState;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.sokoban.Listener.MenuItem;
import eu.kingconquest.sokoban.Listener.MenuListener;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends Menu {
    private final Game game;
    public PauseMenu(Game game){
        this.game = game;
        createMenu();
    }
    @Override
    protected void createMenu() {
        game.setState(GameState.PAUSED);

        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea title = new JTextArea("Pause Menu");
        title.setBackground(new Color(0, 0, 0, 0));
        title.setFont(new Font(null, Font.BOLD, 48));
        title.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        JButton startButton = new JButton("Resume Game");
        startButton.addActionListener(new MenuListener(game, this, MenuItem.RESUME));
        gbc.gridy++;
        add(startButton, gbc);

        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(new MenuListener(game,this, MenuItem.LOAD));
        gbc.gridy++;
        add(loadButton, gbc);

        JButton saveButton = new JButton("Save Game");
        saveButton.addActionListener(new MenuListener(game, this, MenuItem.SAVE));
        gbc.gridy++;
        add(saveButton, gbc);

        // TODO:
        // Add a main screen for Game menu choosing (Platform)
        JButton gameMenuButton = new JButton("Return to game menu");
        gameMenuButton.addActionListener(new MenuListener(game, this, MenuItem.RETURN));
        gbc.gridy++;
        add(gameMenuButton, gbc);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(new MenuListener(game,this, MenuItem.EXIT));
        gbc.gridy++;
        add(exitButton, gbc);

    }
}