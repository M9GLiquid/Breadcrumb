package eu.kingconquest.framework.ui;

import eu.kingconquest.framework.views.GameGuiView;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JPanel view;
    GameGuiView gameGuiView;
    private JPanel previousView;
    public GameFrame() {
        setFocusable(true);
        setResizable(false);
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

    public JPanel getPreviousView() {
        return previousView;
    }

    public void setGameGuiView(GameGuiView gameGuiView){
        this.gameGuiView = gameGuiView;
    }

    public GameGuiView getGameGuiView(){
        return gameGuiView;
    }
}