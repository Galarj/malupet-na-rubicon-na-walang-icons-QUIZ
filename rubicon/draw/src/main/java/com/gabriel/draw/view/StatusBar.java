package com.gabriel.draw.view;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {
    private JLabel statusLabel;
    private JLabel mouseLabel;

    public StatusBar() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEtchedBorder());

        statusLabel = new JLabel("Ready");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

        mouseLabel = new JLabel("x: 0, y: 0");
        mouseLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        this.add(statusLabel, BorderLayout.WEST);
        this.add(mouseLabel, BorderLayout.EAST);
    }


    public void setStatus(String text) {
        statusLabel.setText(text);
        revalidate();
        repaint();
    }

    public void setMousePosition(int x, int y) {
        mouseLabel.setText("x: " + x + ", y: " + y);
    }
}
