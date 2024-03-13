import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A class that provides a customizable menu within a JFrame. It supports adding
 * dynamic toolbars and overlay panels with draggable buttons for interactive user interfaces.
 * The menu utilizes the Material Look and Feel for modern UI elements and supports
 * image icons for buttons.
 */
public class CustomizableMenu {

    /**
     * The main frame in which the customizable menu is displayed.
     */
    JFrame frame;

    /**
     * The panel that appears as an overlay on the frame, providing a space for
     * interactive buttons and custom components.
     */
    JPanel overlayPanel;

    /**
     * A toolbar aligned to the right side of the frame, designed for vertical use.
     */
    JToolBar rightToolBar;

    /**
     * A toolbar placed at the bottom of the frame, intended for additional controls
     * and interactions.
     */
    JToolBar bottomToolBar;

    /**
     * Constructs a CustomizableMenu attached to the specified JFrame. Initializes
     * right and bottom toolbars and configures their default properties.
     *
     * @param frame The JFrame to which the customizable menu is to be added.
     */
    public CustomizableMenu(JFrame frame) {
        this.frame = frame;
        this.rightToolBar = createRightToolBar();
        this.bottomToolBar = new JToolBar(); // Initialize the bottom toolbar
        configureBottomToolBar(); // Configure the bottom toolbar
    }

    /**
     * Creates and returns a toolbar aligned to the right side of the frame.
     * The toolbar is non-floatable and hosts a 'plus' button at the bottom.
     *
     * @return A configured JToolBar positioned vertically.
     */
    public JToolBar createRightToolBar() {
        JToolBar rightToolBar = new JToolBar(JToolBar.VERTICAL);
        rightToolBar.setFloatable(false); // Make the toolbar non-floatable

        JButton plusButton = createPlusButton();

        // Add the plus button with an icon at the bottom
        rightToolBar.add(Box.createVerticalGlue()); // Pushes the plus button to the bottom
        rightToolBar.add(plusButton);

        return rightToolBar;
    }

    /**
     * Configures the bottom toolbar, making it non-floatable and setting its layout.
     * Adds an 'Exit' button to the toolbar for closing the overlay.
     */
    private void configureBottomToolBar() {
        bottomToolBar.setFloatable(false);
        bottomToolBar.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(Color.BLUE);

        exitButton.addActionListener(e -> exitOverlay());

        bottomToolBar.add(exitButton);
        bottomToolBar.setVisible(false);
        bottomToolBar.setBounds(0, frame.getHeight() - 100, frame.getWidth(), 50);

        frame.getLayeredPane().add(bottomToolBar, JLayeredPane.DRAG_LAYER);
    }

    /**
     * Creates and returns a JButton with a 'plus' icon. The button is configured
     * for visual appeal and triggers the display toggle of the overlay panel.
     *
     * @return A JButton configured with a plus icon.
     */
    public JButton createPlusButton() {
        // Load the plus icon
        BufferedImage plusImage = null;
        try {
            plusImage = ImageIO.read(getClass().getResource("/plus-large-svgrepo-com.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Resize the image
        Image resizedImage = plusImage.getScaledInstance(49, 49, Image.SCALE_SMOOTH);
        ImageIcon plusIcon = new ImageIcon(resizedImage);

        JButton plusButton = new JButton(plusIcon);

        plusButton.setFocusPainted(false);
        plusButton.setBorderPainted(false);
        plusButton.setContentAreaFilled(false);
        plusButton.addActionListener(e -> toggleOverlay());

        return plusButton;
    }

    /**
     * Toggles the visibility of the overlay panel and the bottom toolbar, effectively
     * showing or hiding the customizable menu overlay.
     */
    void toggleOverlay() {
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
    void exitOverlay() {
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
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 123));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Adjust the overlay size and add it to the frame
        overlayPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        overlayPanel.setOpaque(false);

        // Define buttons
        String[] buttonTitles = {"Save", "Delete", "Edit", "New", "Share", "Undo", "Redo", "Cut", "Copy", "Paste"};
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
     * Creates a draggable JButton with the specified text. The button can be moved
     * across the overlay and dropped into the bottom toolbar, facilitating a dynamic
     * and interactive UI.
     *
     * @param text The text to be displayed on the button.
     * @return A JButton configured for drag-and-drop functionality.
     */
    private JButton createDraggableButton(String text) {
        JButton button = new JButton(text);
        MouseAdapter ma = new MouseAdapter() {
            private Point offset;

            @Override
            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                Point newLocation = SwingUtilities.convertMouseEvent(e.getComponent(), e, frame.getLayeredPane()).getPoint();
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
                }
            }
        };

        button.addMouseListener(ma);
        button.addMouseMotionListener(ma);

        return button;
    }
}
