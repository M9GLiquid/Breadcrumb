package eu.kingconquest.platform;

import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.sokoban.core.Sokoban;
import eu.kingconquest.twozerofoureight.Game2048;

import javax.swing.*;
import java.awt.*;

public class PlatformMenu extends Menu {
    private final GameFrame gameFrame;

    public PlatformMenu(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        createMenu();
    }
    @Override
    protected void createMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 250, 10, 250);

        JTextArea title = new JTextArea("Choose your game:");
        title.setBackground(new Color(0, 0, 0, 0));
        title.setFont(new Font(null, Font.BOLD, 48));
        title.setFocusable(false);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        JButton gameButton1 = new JButton("Sokoban");
        gameButton1.addActionListener(e -> {
            new Sokoban(gameFrame);
            gameFrame.remove(this);

        });
        gbc.weightx = 1; // Add this line
        gbc.insets = new Insets(50, 250, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Add this line
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(gameButton1, gbc);

        JButton gameButton2 = new JButton("2048");
        gameButton2.addActionListener(e -> {
            new Game2048(gameFrame);
            gameFrame.remove(this);
        });
        gbc.weightx = 1; // Add this line
        gbc.insets = new Insets(50, 0, 10, 250);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Add this line
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(gameButton2, gbc);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(e -> System.exit(0));
        gbc.weightx = 0; // Add this line
        gbc.insets = new Insets(10, 250, 10, 250);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(exitButton, gbc);
    }
}
