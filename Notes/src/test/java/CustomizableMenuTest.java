import javax.swing.*;
import static org.junit.Assert.*;
import org.junit.*;

import view.CustomizableMenu;

import java.awt.*;

/**
 * Unit tests for the {@link CustomizableMenu} class.
 */
public class CustomizableMenuTest {

    private CustomizableMenu menu;
    private JFrame frame;

    /**
     * Sets up the test environment before each test case.
     */
    @Before
    public void setUp() {
        frame = new JFrame();
        menu = new CustomizableMenu(frame);
    }

    /**
     * Tears down the test environment after each test case.
     */
    @After
    public void tearDown() {
        frame.dispose();
    }

    /**
     * Tests whether the right toolbar contains the plus button.
     */
    @Test
    public void testRightToolBarContainsPlusButton() {
        JToolBar rightToolBar = menu.createRightToolBar();
        Component[] components = rightToolBar.getComponents();
        boolean containsPlusButton = false;
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getActionListeners().length > 0) {
                    containsPlusButton = true;
                    break;
                }
            }
        }
        assertTrue(containsPlusButton);
    }

    /**
     * Tests whether the bottom toolbar contains the exit button.
     */
    @Test
    public void testBottomToolBarContainsExitButton() {
        JButton exitButton = null;
        Component[] components = menu.bottomToolBar.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if ("Exit".equals(button.getText())) {
                    exitButton = button;
                    break;
                }
            }
        }
        assertNotNull(exitButton);
    }

    /**
     * Tests whether the plus button is created successfully.
     */
    @Test
    public void testCreatePlusButton() {
        JButton plusButton = menu.createPlusButton();
        assertNotNull(plusButton);
        assertTrue(plusButton.getActionListeners().length > 0);
    }

    /**
     * Tests whether the overlay is properly exited.
     */
    @Test
    public void testExitOverlay() {
        menu.toggleOverlay(); // Make overlay visible
        menu.exitOverlay();
        assertFalse(menu.overlayPanel.isVisible());
        assertFalse(menu.bottomToolBar.isVisible());
    }

    /**
     * Tests whether the right toolbar is added to the frame.
     */
    @Test
    public void testRightToolBarAddedToFrame() {
        assertNotNull(frame.getLayeredPane().getComponent(0));
        assertTrue(frame.getLayeredPane().getComponent(0) instanceof JToolBar);
    }

    /**
     * Tests whether the bottom toolbar is added to the frame.
     */
    @Test
    public void testBottomToolBarAddedToFrame() {
        assertTrue(containsComponent(frame.getLayeredPane(), menu.bottomToolBar));
    }

    /**
     * Helper method to check if a component is present in the layered pane.
     *
     * @param layeredPane The layered pane to check.
     * @param component   The component to search for.
     * @return {@code true} if the component is present, {@code false} otherwise.
     */
    private boolean containsComponent(JLayeredPane layeredPane, Component component) {
        for (Component c : layeredPane.getComponents()) {
            if (c.equals(component)) {
                return true;
            }
        }
        return false;
    }
}
