package eu.kingconquest.UI;

import eu.kingconquest.Core.Sokoban;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StartMenu extends JPanel implements MenuStrategy {
    private final List<MenuObserver> observers;

    public StartMenu() {
        observers = new ArrayList<>();
    }

    @Override
    public JPanel createMenu(Sokoban game) {
        setLayout(new GridBagLayout());
        setBackground(new Color(128, 128, 128, 200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea title = new JTextArea("Sokoban");
        title.setBackground(new Color(0,0,0,0));
        title.setFont(new Font(null, Font.BOLD, 48));
        title.setFocusable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(title, gbc);

        JTextArea subTitle = new JTextArea("may the odds be ever in your favor");
        subTitle.setForeground(Color.LIGHT_GRAY);
        subTitle.setFocusable(false);
        subTitle.setIgnoreRepaint(true);
        subTitle.setBackground(new Color(0,0,0,0));
        subTitle.setFont(new Font(null, Font.ITALIC, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(subTitle, gbc);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            notifyObservers("start");
            setVisible(false);
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(startButton, gbc);

        JButton exitButton = new JButton("Exit Game");
        exitButton.addActionListener(e -> notifyObservers("exit"));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(exitButton, gbc);

        JButton resetButton = new JButton("Restart Game");
        resetButton.addActionListener(e -> notifyObservers("restart"));
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(resetButton, gbc);

        return this;
    }

    @Override
    public void registerObserver(MenuObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(MenuObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String action) {
        for (MenuObserver observer : observers)
            observer.onMenuAction(action);
    }
}
