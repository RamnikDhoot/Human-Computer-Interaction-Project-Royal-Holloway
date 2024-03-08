package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomSidebar extends JPanel {
    private JButton plusButton;

    public CustomSidebar() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeUI();
    }

    private void initializeUI() {
        add(Box.createVerticalGlue());
    
        plusButton = new JButton("+") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.LIGHT_GRAY); // Pressed color
                } else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
                super.paintComponent(g);
            }
    
            // Paint a border for the button
            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
            }
        };
    
        plusButton.setContentAreaFilled(false);
        plusButton.setBorderPainted(false);
        plusButton.setFocusPainted(false);
    
        Dimension size = new Dimension(50, 50); 
        plusButton.setPreferredSize(size);
        plusButton.setMinimumSize(size);
        plusButton.setMaximumSize(size);
    
        add(plusButton);
    
        plusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    

    private void openFunctionChooser() {
 
        JButton newFunctionButton = new JButton("New Function");
        add(newFunctionButton, getComponentCount() - 1);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (plusButton != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(plusButton.getBackground());
            // Draw a filled circle for the background of the button
            g2d.fillOval(plusButton.getBounds().x, plusButton.getBounds().y, plusButton.getBounds().width, plusButton.getBounds().height);
            g2d.dispose();
        }
    }
}
