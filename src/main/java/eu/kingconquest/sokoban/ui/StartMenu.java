package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.core.Game;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.sokoban.Listener.MenuItem;
import eu.kingconquest.sokoban.Listener.MenuListener;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends Menu {

    public StartMenu(Game game) {
        super(game);
    }

    @Override
    public void createMenu() {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea title = new JTextArea(game.getTitle());
        title.setBackground(new Color(0, 0, 0, 0));
        title.setFont(new Font(null, Font.BOLD, 48));
        title.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        JTextArea subTitle = new JTextArea("may the odds be ever in your favor");
        subTitle.setForeground(Color.LIGHT_GRAY);
        subTitle.setFocusable(false);
        subTitle.setIgnoreRepaint(true);
        subTitle.setBackground(new Color(0, 0, 0, 0));
        subTitle.setFont(new Font(null, Font.ITALIC, 24));
        gbc.gridx = 0;
        gbc.gridy++;
        add(subTitle, gbc);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new MenuListener(game, this, MenuItem.START));
        gbc.gridy++;
        add(startButton, gbc);

        JButton loadButton = new JButton("Load Game");
        loadButton.addActionListener(new MenuListener(game,this, MenuItem.LOAD));
        gbc.gridy++;
        add(loadButton, gbc);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(new MenuListener(game,this, MenuItem.EXIT));
        gbc.gridy++;
        add(exitButton, gbc);
    }
}
