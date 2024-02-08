import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ToolBarHandler;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

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
}
