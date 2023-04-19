package eu.kingconquest.sokoban.ui;

import eu.kingconquest.framework.Game;
import eu.kingconquest.framework.GamePanel;
import eu.kingconquest.framework.ui.Menu;
import eu.kingconquest.sokoban.core.StateObserver;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends Menu {

    public StartMenu(Game game, GamePanel panel) {
        super(game, panel);
        game.registerObserver(new StateObserver(game));
    }

    @Override
    public void createMenu() {
        JFrame frame = panel.frame;
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea title = new JTextArea("Sokoban");
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
        gbc.gridy = 1;
        add(subTitle, gbc);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(event -> {
            game.start();
            frame.getContentPane().removeAll();
            frame.add(panel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            panel.requestFocusInWindow();
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(startButton, gbc);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(event -> System.exit(0));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(exitButton, gbc);
    }
}
