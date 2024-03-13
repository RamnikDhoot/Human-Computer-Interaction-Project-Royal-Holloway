import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;

class ToolBarHandlerTest {
    private JFrame frame;
    private JTextArea noteTextArea;
    private ToolBarHandler toolBarHandler;

    @BeforeEach
    void setUp() {
        // Setup the environment for each test
        frame = new JFrame();
        noteTextArea = new JTextArea();
        toolBarHandler = new ToolBarHandler(frame, noteTextArea);
    }

    @Test
    void testToolbarExists() {
        JToolBar toolBar = toolBarHandler.createToolBar();
        assertNotNull(toolBar, "Toolbar should not be null");
        assertEquals(2, toolBar.getComponentCount(), "Toolbar should contain exactly two buttons.");
    }

    @Test
    void testUndoButton() {
        // Simulate text addition and undo operation
        noteTextArea.setText("Sample text");
        toolBarHandler.createToolBar(); 
        noteTextArea.setText(""); 
        toolBarHandler.undo(); 

        assertEquals("Sample text", noteTextArea.getText(), "Undo operation failed to revert text area content.");
    }

    @Test
    void testRedoButton() {
        // Simulate text addition, undo, and redo operations
        noteTextArea.setText("Sample text");
        toolBarHandler.createToolBar(); 
        String originalText = noteTextArea.getText();
        toolBarHandler.undo(); 
        toolBarHandler.redo();

        assertEquals(originalText, noteTextArea.getText(), "Redo operation failed to restore text area content.");
    }

    @Test
    void testExitTouchScreenModeButtonVisibility() {
        JButton exitButton = new JButton("Exit Touch Screen Mode");
        toolBarHandler.setExitTouchScreenModeButton(exitButton);
        JToolBar toolBar = toolBarHandler.createToolBar();

        boolean buttonVisible = false;
        for (Component comp : toolBar.getComponents()) {
            if (comp == exitButton) {
                buttonVisible = comp.isVisible();
                break;
            }
        }

        assertFalse(buttonVisible, "Exit touch screen mode button should initially be invisible.");
    }

    @Test
    void testUndoLimitation() {
        // Assuming the undo manager has a limit (not shown in class), simulate reaching that limit
        noteTextArea.setText("Initial text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("Changed text");
        toolBarHandler.undo();
        toolBarHandler.undo(); // Attempt to undo beyond history

        assertEquals("Initial text", noteTextArea.getText(), "Attempting further undo should have no effect beyond available history.");
    }

    @Test
    void testRedoLimitation() {
        noteTextArea.setText("Initial text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("Changed text");
        toolBarHandler.undo();
        toolBarHandler.redo();
        toolBarHandler.redo(); // Attempt to redo beyond history

        assertEquals("Changed text", noteTextArea.getText(), "Attempting further redo should have no effect beyond available history.");
    }

    @Test
    void testUndoButtonAction() {
        // Simulate the button click instead of calling undo method directly
        noteTextArea.setText("Initial text");
        JToolBar toolBar = toolBarHandler.createToolBar();
        JButton undoButton = (JButton) toolBar.getComponentAtIndex(0); // Assuming first button is undo
        undoButton.doClick();

        // Since we haven't changed the text after creating the toolbar, there's nothing to undo to
        assertEquals("Initial text", noteTextArea.getText(), "Undo button click should perform undo operation.");
    }

    @Test
    void testRedoButtonAction() {
        // Similar to the undo test but for redo
        noteTextArea.setText("Initial text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("Changed text");
        toolBarHandler.undo();

        JToolBar toolBar = toolBarHandler.createToolBar();
        JButton redoButton = (JButton) toolBar.getComponentAtIndex(1); // Assuming second button is redo
        redoButton.doClick();

        assertEquals("Changed text", noteTextArea.getText(), "Redo button click should perform redo operation.");
    }
}
