package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.Game;
import eu.kingconquest.framework.GameState;
import eu.kingconquest.framework.io.DataReader;
import eu.kingconquest.framework.ui.Menu;

import javax.swing.*;
import java.awt.*;

public class PauseMenu extends Menu {
    public PauseMenu(Game game){
        super(game);
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
        startButton.addActionListener(event -> {
            gameFrame.remove(this);
            game.setState(GameState.RUNNING);
            gameFrame.addView(gameFrame.view, game.getBoard().COLS, game.getBoard().ROWS);

        });
        gbc.gridy++;
        add(startButton, gbc);

        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(event -> {
            game.start();
            DataReader.read(game);
        });
        gbc.gridy++;
        add(loadButton, gbc);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(event -> System.exit(0));
        gbc.gridy++;
        add(exitButton, gbc);

    }
}
