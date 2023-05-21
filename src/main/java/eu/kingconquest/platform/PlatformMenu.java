package eu.kingconquest.platform;

import eu.kingconquest.framework.ui.GameFrame;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.platform.listeners.PlatformListener;

import java.awt.*;

public class PlatformMenu extends Menu {
    private final GameFrame gameFrame;

    public PlatformMenu(GameFrame gameFrame){
        this.gameFrame = gameFrame;
        createMenu();
    }
    @Override
    protected void createMenu() {
        // Setup Layout
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 250, 10, 250);

        // Label to display text
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(textComponent("Choose your game:", 48, false), gbc);

        gbc.weightx = 1; // Add this line
        gbc.insets = new Insets(50, 250, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Add this line
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(buttonComponent("Sokoban",
                        new PlatformListener(gameFrame, this)),
                gbc);

        gbc.weightx = 1; // Add this line
        gbc.insets = new Insets(50, 0, 10, 250);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Add this line
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(buttonComponent("2048",
                        new PlatformListener(gameFrame, this)), gbc);

        gbc.weightx = 1; // Add this line
        gbc.insets = new Insets(50, 0, 10, 250);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Add this line
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(buttonComponent("Maze",
                        new PlatformListener(gameFrame, this)),
                gbc);

        gbc.weightx = 0; // Add this line
        gbc.insets = new Insets(10, 250, 10, 250);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(buttonComponent("Exit",
                new PlatformListener(gameFrame, this)), gbc);
    }
}
