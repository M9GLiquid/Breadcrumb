package eu.kingconquest.framework.ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private JPanel view;
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
        changeSize(width, height);
        setLocationRelativeTo(null);
        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        view = panel;
        revalidate();
        repaint();
        requestFocusInWindow();
    }

    public void changeSize(int width, int height){
        setSize(width, height);
    }

    public JPanel getView() {
        return view;
    }
}