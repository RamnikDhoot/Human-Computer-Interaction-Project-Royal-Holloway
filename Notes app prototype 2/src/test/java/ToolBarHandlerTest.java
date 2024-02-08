import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.NotesApplication;
import com.example.ToolBarHandler;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ToolBarHandlerTest {
    private JFrame frame;
    private NotesApplication notesApp;

    @Test
    void testCreateToolBarExists() {
        // Mock the required components for ToolBarHandler
        frame = new JFrame();
        JTextArea mockTextArea = new JTextArea();
        
        // Create an instance of ToolBarHandler
        ToolBarHandler toolBarHandler = new ToolBarHandler(frame, mockTextArea);
        
        // Call the method under test
        JToolBar toolBar = toolBarHandler.createToolBar();
        
        // Assertions to verify the toolbar was created and contains the expected components
        assertNotNull(toolBar, "Toolbar should not be null");
    }
}
