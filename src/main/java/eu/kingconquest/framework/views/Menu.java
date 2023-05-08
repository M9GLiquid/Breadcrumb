package eu.kingconquest.framework.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Menu extends JPanel {
    protected abstract void createMenu();

    protected JComponent textComponent(String string, int fontSize, boolean isSubTitle){
        JTextArea title = new JTextArea(string);

        if (isSubTitle)
            title.setForeground(Color.LIGHT_GRAY);
        else
            title.setForeground(Color.BLACK);

        title.setFocusable(false);
        title.setIgnoreRepaint(true);
        title.setBackground(new Color(0, 0, 0, 0));
        title.setFont(new Font(null, Font.BOLD, fontSize));
        return title;
    }


    protected JComponent buttonComponent(String string, ActionListener menuListener){
        JButton gameMenuButton = new JButton(string);
        gameMenuButton.addActionListener(menuListener);
        return gameMenuButton;
    }
}