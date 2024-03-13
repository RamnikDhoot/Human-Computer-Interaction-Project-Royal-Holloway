import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.*;

public class CustomizableMenuTest {

    private JFrame frame;
    private CustomizableMenu customizableMenu;

    @Before
    public void setUp() {
        // Setup your test environment
        frame = new JFrame();
        customizableMenu = new CustomizableMenu(frame);
    }

    @Test
    public void testCreateRightToolBarReturnsToolBar() {
        JToolBar rightToolBar = customizableMenu.createRightToolBar();
        assertNotNull("Right toolbar should not be null", rightToolBar);
        assertEquals("Toolbar orientation should be vertical", JToolBar.VERTICAL, rightToolBar.getOrientation());
        assertFalse("Toolbar should not be floatable", rightToolBar.isFloatable());
    }

    @Test
    public void testCreatePlusButton() {
        JToolBar rightToolBar = customizableMenu.createRightToolBar();
        JButton plusButton = (JButton) rightToolBar.getComponent(rightToolBar.getComponentCount() - 1);

        assertNotNull("Plus button should not be null", plusButton);
    }

}
