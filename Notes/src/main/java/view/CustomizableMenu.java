package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The {@code CustomizableMenu} class implements a customizable menu within a
 * {@code JFrame}.
 * It facilitates adding dynamic toolbars and overlay panels with draggable
 * buttons for interactive user interfaces.
 * The class leverages Material Look and Feel for modern UI elements and
 * supports image icons for buttons.
 * This class is suitable for creating applications requiring flexible and
 * interactive user interfaces.
 */
public class CustomizableMenu {
    JFrame frame;
    public JPanel overlayPanel;
    JToolBar rightToolBar;
    public JToolBar bottomToolBar;

    /**
     * Constructs a {@code CustomizableMenu} attached to the specified
     * {@code JFrame}.
     * Initializes right and bottom toolbars and configures their default
     * properties.
     *
     * @param frame The {@code JFrame} to which the customizable menu is to be
     *              added.
     */
    public CustomizableMenu(JFrame frame) {
        this.frame = frame;
        this.rightToolBar = createRightToolBar();
        this.bottomToolBar = new JToolBar(); // Initialize the bottom toolbar
        configureBottomToolBar(); // Configure the bottom toolbar
    }

    /**
     * Creates and returns a JButton with specified properties including text, icon,
     * action listener, and mouse adapter for enhanced interactivity.
     *
     * @param text           The text of the button.
     * @param icon           The icon to be displayed on the button.
     * @param actionListener The action listener for button actions.
     * @param mouseAdapter   The mouse adapter for mouse events.
     * @return A configured {@code JButton} instance.
     */
    private JButton createButton(String text, Icon icon, ActionListener actionListener, MouseAdapter mouseAdapter) {
        JButton button = new JButton(text, icon);
        button.setForeground(Color.BLUE); // Set the text color to blue
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        if (mouseAdapter != null) { // Add mouse listeners to the button, ensuring the adapter is valid
            button.addMouseListener(mouseAdapter);
            button.addMouseMotionListener(mouseAdapter);
        }
        return button;
    }

    /**
     * Creates and configures a {@code JToolBar} aligned to the right side of the
     * frame.
     * It is designed for vertical alignment and hosts a customizable 'plus' button
     * at the bottom.
     *
     * @return A {@code JToolBar} instance configured for vertical alignment.
     */
    public JToolBar createRightToolBar() {
        JToolBar rightToolBar = new JToolBar(JToolBar.VERTICAL);
        rightToolBar.setFloatable(false); // Prevent the right toolbar from becoming a floating window

        JButton plusButton = createPlusButton();

        // Add the plus button with an icon at the bottom
        rightToolBar.add(Box.createVerticalGlue()); // Pushes the plus button to the bottom
        rightToolBar.add(plusButton);

        return rightToolBar;
    }

    /**
     * Configures the bottom toolbar, making it non-floatable and setting its
     * layout.
     * Adds an 'Exit' button to the toolbar for closing the overlay.
     */
    private void configureBottomToolBar() {
        bottomToolBar.setFloatable(false);
        bottomToolBar.setLayout(new FlowLayout(FlowLayout.CENTER)); // Arrange components within the bottom toolbar in a
                                                                    // centered flow

        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(Color.BLUE);

        exitButton.addActionListener(e -> exitOverlay());

        bottomToolBar.add(exitButton);
        bottomToolBar.setVisible(false);
        bottomToolBar.setBounds(0, frame.getHeight() - 100, frame.getWidth(), 50);

        frame.getLayeredPane().add(bottomToolBar, JLayeredPane.DRAG_LAYER);
    }

    /**
     * Loads an image icon from a given path and scales it to the specified width
     * and height.
     *
     * @param path   The path to the image resource.
     * @param width  The desired width of the icon.
     * @param height The desired height of the icon.
     * @return An {@code ImageIcon} instance of the scaled image.
     */
    private ImageIcon loadIcon(String path, int width, int height) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Creates a 'plus' button with an icon, designed to toggle the overlay panel
     * visibility.
     *
     * @return A {@code JButton} instance configured with a 'plus' icon.
     */
    public JButton createPlusButton() {
        ImageIcon plusIcon = loadIcon("/plus-large-svgrepo-com.png", 49, 49);
        // Passing an empty string for text as the button uses an icon.
        // No MouseAdapter is needed so pass null.
        return createButton("", plusIcon, e -> toggleOverlay(), null);
    }

    /**
     * Toggles the visibility of the overlay panel and the bottom toolbar,
     * effectively
     * showing or hiding the customizable menu overlay.
     */
    public void toggleOverlay() {
        if (overlayPanel == null) {
            createOverlay();
        }

        boolean isVisible = !overlayPanel.isVisible();
        overlayPanel.setVisible(isVisible);
        bottomToolBar.setVisible(isVisible);

        if (isVisible) {
            frame.getLayeredPane().setLayer(overlayPanel, JLayeredPane.MODAL_LAYER);
            frame.getLayeredPane().setLayer(rightToolBar, JLayeredPane.DRAG_LAYER);
            frame.getLayeredPane().setLayer(bottomToolBar, JLayeredPane.DRAG_LAYER);
        }

        frame.getLayeredPane().revalidate();
        frame.getLayeredPane().repaint();
    }

    /**
     * Closes the overlay by hiding the overlay panel and the bottom toolbar.
     */
    public void exitOverlay() {
        overlayPanel.setVisible(false);
        bottomToolBar.setVisible(false);
    }

    /**
     * Creates the overlay panel with a semi-transparent background and populates it
     * with a set of predefined buttons. These buttons can be dragged and dropped
     * across the overlay, providing a customizable user interface.
     */
    private void createOverlay() {
        overlayPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Call the parent class's paintComponent method
                g.setColor(new Color(0, 0, 0, 123));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Adjust the overlay size and add it to the frame
        overlayPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        overlayPanel.setOpaque(false);

        // Define buttons
        String[] buttonTitles = { "Save", "Delete", "Edit", "New", "Share", "Undo", "Redo", "Cut", "Copy", "Paste" };
        JButton[] buttons = new JButton[buttonTitles.length];

        // Calculate spacing and button size
        int buttonWidth = 70;
        int buttonHeight = 40;
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

        /// After setting up the overlay
        frame.getLayeredPane().add(overlayPanel, JLayeredPane.MODAL_LAYER);

        // Ensure the right toolbar and bottom toolbar are above the overlay
        frame.getLayeredPane().add(rightToolBar, JLayeredPane.DRAG_LAYER);
        frame.getLayeredPane().add(bottomToolBar, JLayeredPane.DRAG_LAYER);

        frame.getLayeredPane().revalidate();
        frame.getLayeredPane().repaint();
    }

    /**
     * Creates a draggable button with a specified text label. This button can be
     * moved across the overlay panel,
     * allowing for a flexible and user-customizable interface. The method handles
     * mouse events to enable drag-and-drop functionality.
     *
     * @param text The text for the button's text label.
     *
     * @return A JButton that can be dragged and dropped within the application's
     *         UI.
     */
    private JButton createDraggableButton(String text) {
        MouseAdapter ma = new MouseAdapter() {
            private Point offset;

            @Override
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                Point newLocation = SwingUtilities.convertMouseEvent(e.getComponent(), e, frame.getLayeredPane())
                        .getPoint();
                newLocation.translate(-offset.x, -offset.y);
                button.setLocation(newLocation);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                Point dropLocation = button.getLocation();

                // Check if the button was released over the bottom toolbar
                if (dropLocation.y + button.getHeight() >= bottomToolBar.getLocation().y &&
                        dropLocation.y <= bottomToolBar.getLocation().y + bottomToolBar.getHeight()) {

                    // Add button to bottom toolbar
                    bottomToolBar.add(button);
                    bottomToolBar.revalidate();
                    bottomToolBar.repaint();

                    // Remove the button from its original parent
                    overlayPanel.remove(button);
                    overlayPanel.revalidate();
                    overlayPanel.repaint();
                } // Help for this method from
                  // https://stackoverflow.com/questions/77218050/is-there-a-method-to-set-the-background-color-of-a-jbutton-while-its-pressed
            }// https://coderanch.com/t/536055/java/mouseClicked-mouseDragged
        };

        // No icon and action listener for a draggable button, so pass null for those
        // parameters
        return createButton(text, null, null, ma);
    }

}
