package com.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CustomizableMenu {

    private JFrame frame;
    private JPanel overlayPanel;

    public CustomizableMenu(JFrame frame) {
        this.frame = frame;
    }

    public JToolBar createRightToolBar() {
        JToolBar rightToolBar = new JToolBar(JToolBar.VERTICAL);
        rightToolBar.setFloatable(false); // Make the toolbar non-floatable

        JButton plusButton = createPlusButton();

        // Add the plus button with an icon at the bottom
        rightToolBar.add(Box.createVerticalGlue()); // Pushes the plus button to the bottom
        rightToolBar.add(plusButton);

        return rightToolBar;
    }

    private JButton createPlusButton() {
        // Load the plus icon
        BufferedImage plusImage = null;
        try {
            plusImage = ImageIO.read(getClass().getResource("/plus-large-svgrepo-com.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Resize the image
        Image resizedImage = plusImage.getScaledInstance(49, 49, Image.SCALE_SMOOTH); // Adjust width and height as needed
        ImageIcon plusIcon = new ImageIcon(resizedImage);

        // Create the plus button with the resized icon
        JButton plusButton = new JButton(plusIcon);

        plusButton.setFocusPainted(false);
        plusButton.setBorderPainted(false);
        plusButton.setContentAreaFilled(false);
        plusButton.addActionListener(e -> toggleOverlay());

        return plusButton;
    }

    private void toggleOverlay() {
        if (overlayPanel == null) {
            createOverlay();
        }

        // Toggle visibility
        overlayPanel.setVisible(!overlayPanel.isVisible());
    }

    private void createOverlay() {
        overlayPanel = new JPanel(null) { // Use null layout for absolute positioning
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 123));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Define buttons and their titles
        String[] buttonTitles = {"Save", "Delete", "Edit", "New", "Share"};
        JButton[] buttons = new JButton[buttonTitles.length];

        // Calculate spacing and button size
        int buttonWidth = 100;
        int buttonHeight = 100;
        int totalWidth = (buttonWidth * buttons.length);
        int spacing = (frame.getWidth() - totalWidth) / (buttons.length + 1);
        int yPosition = 50; // Vertical position of buttons

        // Create and position buttons
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = createDraggableButton(buttonTitles[i]);
            int xPosition = spacing + (i * (buttonWidth + spacing));
            buttons[i].setBounds(xPosition, yPosition, buttonWidth, buttonHeight);
            overlayPanel.add(buttons[i]);
        }

        // Adjust the overlay size and add it to the frame
        overlayPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        overlayPanel.setOpaque(false);
        frame.getLayeredPane().add(overlayPanel, JLayeredPane.PALETTE_LAYER, 0);


    }

    private JButton createDraggableButton(String text) {
        final Color backgroundColor = new Color(33, 150, 243, 200); // Semi-transparent blue
        final Color textColor = Color.WHITE; // Text color
        final Font buttonFont = new Font("Roboto", Font.BOLD, 10); // Font for the button text

        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                // Fill the button with a semi-transparent color
                g.setColor(backgroundColor);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g); // This will paint the label text over the background
            }
        };

        // Set button properties
        button.setForeground(textColor);
        button.setFont(buttonFont);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false); 
        button.setBorder(null); 

        // Set button margins and size
        int buttonWidth = 100; 
        int buttonHeight = 30; 
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setMinimumSize(button.getPreferredSize());
        button.setMaximumSize(button.getPreferredSize());

        // Mouse listeners for the dragging behavior
        MouseAdapter ma = new MouseAdapter() {
            private Point offset;

            @Override
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                Point newLocation = e.getLocationOnScreen();
                SwingUtilities.convertPointFromScreen(newLocation, overlayPanel);
                newLocation.translate(-offset.x, -offset.y);
                button.setLocation(newLocation);
            }
        };

        button.addMouseListener(ma);
        button.addMouseMotionListener(ma);

        return button;
    }

}
