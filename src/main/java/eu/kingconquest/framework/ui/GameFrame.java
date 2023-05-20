package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.views.GameGuiView;

import javax.swing.*;
import java.awt.*;

/**
 * The GameFrame class extends JFrame and serves as the main window for the game. It manages
 * the views to be displayed during different states of the game, like menus, game screens, etc.
 * This class provides methods to add new views, switch between views and get the current view.
 */
public class GameFrame extends JFrame {
    private JPanel view;
    GameGuiView gameView;
    private JPanel previousView;

    /**
     * Constructs a new GameFrame with default settings. The game frame is initially
     * set to be focusable, non-resizable, without decoration, and positioned at the center of the screen.
     * The close operation is set to EXIT_ON_CLOSE, and the layout is set to BorderLayout.
     * Finally, the frame is made visible.
     */
    public GameFrame() {
        setFocusable(true);
        setResizable(false);
        setAutoRequestFocus(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
    }

    /**
     * Adds a Component to the game frame.
     *
     * @param panel the Component to be added
     */
    public void addView(JPanel panel, int width, int height) {
        previousView = view;
        view = panel;

        getContentPane().removeAll();
        if (panel != null) {
            add(panel, BorderLayout.CENTER);
            setSize(width, height);
        }else{
            setSize(0,0);
        }
        setLocationRelativeTo(null);
        revalidate();
        repaint();
        requestFocusInWindow();
    }

    /**
     * Retrieves the previously displayed view on the game frame.
     *
     * @return the previous JPanel view
     */
    public JPanel getPreviousView() {
        return previousView;
    }

    /**
     * Sets the GameGuiView associated with this game frame.
     * This view is typically used to display the actual game content.
     *
     * @param gameView the GameGuiView to be associated with this game frame
     */
    public void setGameView(GameGuiView gameView){
        this.gameView = gameView;
    }

    /**
     * Retrieves the GameGuiView associated with this game frame.
     * This view is typically used to display the actual game content.
     *
     * @return the GameGuiView associated with this game frame
     */
    public GameGuiView getGameView(){
        return gameView;
    }
}