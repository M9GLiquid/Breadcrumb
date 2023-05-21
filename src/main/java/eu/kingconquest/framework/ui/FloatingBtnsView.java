package eu.kingconquest.framework.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FloatingBtnsView extends JDialog{
    public FloatingBtnsView(GameFrame frame, ActionListener listener) {
        super(frame);
        setLocationRelativeTo(frame);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Up button
        JButton upButton = new JButton("UP");
        upButton.setActionCommand("UP");
        upButton.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(upButton, constraints);

        // Left button
        JButton leftButton = new JButton("LEFT");
        leftButton.setActionCommand("LEFT");
        leftButton.addActionListener(listener);
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(leftButton, constraints);

        // Down button
        JButton downButton = new JButton("DOWN");
        downButton.setActionCommand("DOWN");
        downButton.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(downButton, constraints);

        // Right button
        JButton rightButton = new JButton("RIGHT");
        rightButton.setActionCommand("RIGHT");
        rightButton.addActionListener(listener);
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(rightButton, constraints);

        pack();
        setVisible(true);
    }
}
