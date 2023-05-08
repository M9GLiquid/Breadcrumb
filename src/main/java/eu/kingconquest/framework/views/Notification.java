package eu.kingconquest.framework.views;

import eu.kingconquest.framework.core.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Class to show a game saved notification on a timer
 */
public class Notification {

    /**
     * Short timed window to notify the user that the game has indeed been saved.
     * Time is set to 3 seconds
     */
    public static void showNotification(Game game, String content) {
        JWindow saveWindow = new JWindow();
        saveWindow.setAlwaysOnTop(true);
        saveWindow.setSize(300, 100);

        // Set transparent background
        saveWindow.setBackground(new Color(0, 0, 0, 0));

        // Set the location of the message to be just above bottom of screen
        int x = game.getGameFrame().getX() + (game.getGameFrame().getWidth() / 2) - (saveWindow.getWidth() / 2);
        int y = game.getGameFrame().getY() + game.getGameFrame().getHeight() - saveWindow.getHeight();
        saveWindow.setLocation(x ,y);

        // Text to display
        JLabel label = new JLabel(content, JLabel.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        saveWindow.add(label);

        // Set a timer for the window to disappear after 3 seconds
        int delay = 3000; // 3 seconds
        Timer timer = new Timer(delay, e -> saveWindow.dispose());
        timer.setRepeats(false);
        timer.start();

        saveWindow.setVisible(true);
    }

}