package eu.kingconquest.framework.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Abstract Menu class that represents a menu in the game UI.
 * It provides the skeleton for creating menus, including a method for creating a text component
 * and a button component. Width and height are defined as constants.
 */
public abstract class Menu extends JPanel {
    public static short WIDTH = 970;
    public static short HEIGHT = 640;

    /**
     * Creates a menu. Specific implementation is left to the subclasses.
     */
    protected abstract void createMenu();

    /**
     * Creates a text component with the specified string and font size.
     * It can optionally be set as a subtitle.
     *
     * @param string the text to be displayed
     * @param fontSize the size of the font
     * @param isSubTitle a boolean flag indicating whether the text is a subtitle
     * @return a JComponent representing the created text component
     */
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

    /**
     * Creates a button component with the specified string. The button is registered with a
     * provided ActionListener.
     *
     * @param string the text to be displayed on the button
     * @param menuListener the ActionListener to be registered with the button
     * @return a JComponent representing the created button component
     */
    protected JComponent buttonComponent(String string, ActionListener menuListener){
        JButton gameMenuButton = new JButton(string);
        gameMenuButton.setActionCommand(string);
        gameMenuButton.addActionListener(menuListener);
        return gameMenuButton;
    }
}