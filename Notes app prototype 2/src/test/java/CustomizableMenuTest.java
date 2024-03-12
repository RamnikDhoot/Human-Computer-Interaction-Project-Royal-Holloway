import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.CustomizableMenu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CustomizableMenuTest {

    private JFrame frame;
    private CustomizableMenu customizableMenu;

    @Before
    public void setUp() {
        frame = mock(JFrame.class);
        customizableMenu = new CustomizableMenu(frame);
    }

    @Test
    public void testCreateRightToolBar() {
        JToolBar rightToolBar = customizableMenu.createRightToolBar();
        assertNotNull(rightToolBar);
        assertFalse(rightToolBar.isFloatable());
        // Additional assertions for toolbar properties and children
    }

    @Test
    public void testPlusButtonProperties() {
        JToolBar rightToolBar = customizableMenu.createRightToolBar();
        JButton plusButton = (JButton) Arrays.stream(rightToolBar.getComponents())
                                              .filter(component -> component instanceof JButton)
                                              .findFirst()
                                              .orElse(null);

        assertNotNull("Plus button should exist", plusButton);
        // Test for specific properties like icon, borders, action listeners, etc.
    }

    @Test
    public void testOverlayVisibilityToggle() throws Exception {
        // Indirectly trigger toggleOverlay and check the overlayPanel's visibility
        JToolBar rightToolBar = customizableMenu.createRightToolBar();
        JButton plusButton = (JButton) Arrays.stream(rightToolBar.getComponents())
                                              .filter(component -> component instanceof JButton)
                                              .findFirst()
                                              .get();
        plusButton.doClick();

        // Access the overlayPanel field via reflection
        Field overlayPanelField = CustomizableMenu.class.getDeclaredField("overlayPanel");
        overlayPanelField.setAccessible(true);
        JPanel overlayPanel = (JPanel) overlayPanelField.get(customizableMenu);

        assertNotNull("Overlay panel should be created", overlayPanel);
        assertTrue("Overlay panel should be visible after plus button click", overlayPanel.isVisible());
    }

    @Test
    public void testOverlayContainsButtons() throws Exception {
        // Simulate conditions that lead to overlay creation
        customizableMenu.createRightToolBar().getComponent(1).doClick(); // Assuming the plus button is the second component

        // Access the overlayPanel field via reflection
        Field overlayPanelField = CustomizableMenu.class.getDeclaredField("overlayPanel");
        overlayPanelField.setAccessible(true);
        JPanel overlayPanel = (JPanel) overlayPanelField.get(customizableMenu);

        assertTrue("Overlay should contain buttons", overlayPanel.getComponentCount() > 0);
        assertTrue("First component should be a JButton", overlayPanel.getComponent(0) instanceof JButton);
    }}
