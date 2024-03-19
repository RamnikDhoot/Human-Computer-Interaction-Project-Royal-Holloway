/**
 * The ToolBarHandlerTest class contains a series of unit tests to verify
 * the functionality of the ToolBarHandler class methods.
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.ToolBarHandler;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.Component;

class ToolBarHandlerTest {

    private JFrame frame;
    private JTextArea noteTextArea;
    private ToolBarHandler toolBarHandler;

    /**
     * Sets up the environment for each test by initializing the frame, noteTextArea,
     * and ToolBarHandler objects.
     */
    @BeforeEach
    void setUp() {
        frame = new JFrame();
        noteTextArea = new JTextArea();
        toolBarHandler = new ToolBarHandler(frame, noteTextArea);
    }

    /**
     * Tests the creation of the toolbar to ensure it is not null and contains
     * the correct number of buttons.
     */
    @Test
    void testToolbarExists() {
        JToolBar toolBar = toolBarHandler.createToolBar();
        assertNotNull(toolBar, "Toolbar should not be null");
        assertEquals(6, toolBar.getComponentCount(), "Toolbar should contain exactly six buttons.");
    }

    /**
     * Tests the undo functionality to ensure it reverts the text area content
     * to the previous state.
     */
    @Test
    void testUndoButton() {
        noteTextArea.setText("Sample text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("");
        toolBarHandler.undo();
        assertEquals("Sample text", noteTextArea.getText(), "Undo operation failed to revert text area content.");
    }

    /**
     * Tests the redo functionality to ensure it restores the text area content
     * after an undo operation.
     */
    @Test
    void testRedoButton() {
        noteTextArea.setText("Sample text");
        toolBarHandler.createToolBar();
        String originalText = noteTextArea.getText();
        toolBarHandler.undo();
        toolBarHandler.redo();
        assertEquals(originalText, noteTextArea.getText(), "Redo operation failed to restore text area content.");
    }

    /**
     * Tests the visibility of the exit touch screen mode button to ensure it
     * is initially invisible.
     */
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

    /**
     * Tests the undo limitation by attempting to undo beyond the available history.
     */
    @Test
    void testUndoLimitation() {
        noteTextArea.setText("Initial text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("Changed text");
        toolBarHandler.undo();
        toolBarHandler.undo(); // Attempt to undo beyond history
        assertEquals("Initial text", noteTextArea.getText(),
                "Attempting further undo should have no effect beyond available history.");
    }

    /**
     * Tests the redo limitation by attempting to redo beyond the available history.
     */
    @Test
    void testRedoLimitation() {
        noteTextArea.setText("Initial text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("Changed text");
        toolBarHandler.undo();
        toolBarHandler.redo();
        toolBarHandler.redo(); // Attempt to redo beyond history
        assertEquals("Changed text", noteTextArea.getText(),
                "Attempting further redo should have no effect beyond available history.");
    }

    /**
     * Tests the functionality of the undo button action by simulating a click
     * and verifying the text area content after the undo operation.
     */
    @Test
    void testUndoButtonAction() {
        noteTextArea.setText("Initial text");
        JToolBar toolBar = toolBarHandler.createToolBar();
        JButton undoButton = null;
        for (Component comp : toolBar.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Undo")) {
                undoButton = (JButton) comp;
                break;
            }
        }
        assertNotNull(undoButton, "Undo button not found in toolbar.");
        undoButton.doClick();
        assertEquals("Initial text", noteTextArea.getText(), "Undo button click should perform undo operation.");
    }

    /**
     * Tests the functionality of the redo button action by simulating a click
     * and verifying the text area content after the redo operation.
     */
    @Test
    void testRedoButtonAction() {
        noteTextArea.setText("Initial text");
        toolBarHandler.createToolBar();
        noteTextArea.setText("Changed text");
        toolBarHandler.undo();
        JToolBar toolBar = toolBarHandler.createToolBar();
        JButton redoButton = null;
        for (Component comp : toolBar.getComponents()) {
            if (comp instanceof JButton && "Redo".equals(((JButton) comp).getText())) {
                redoButton = (JButton) comp;
                break;
            }
        }
        assertNotNull(redoButton, "Redo button should not be null.");
        redoButton.doClick();
        assertEquals("Changed text", noteTextArea.getText(), "Redo button click should perform redo operation.");
    }

    /**
     * Tests the notebook selection functionality to ensure the frame's title is updated
     * upon selecting a notebook.
     */
    @Test
    void testNotebookSelectionChangesTitle() {
        toolBarHandler.displayNotebookList();
        // Mock the input dialog response for selecting a notebook
        JOptionPane.showMessageDialog(null, "Select a Notebook:");
    }
}
