import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.FileMenuHandler;

import javax.swing.*;
import java.io.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link FileMenuHandler} class.
 */
class FileMenuHandlerTest {
    private JFrame frame;
    private JTextArea noteTextArea;
    private FileMenuHandler fileMenuHandler;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        frame = new JFrame();
        noteTextArea = new JTextArea();
        fileMenuHandler = new FileMenuHandler(frame, noteTextArea);
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tears down the test environment after each test case.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Tests the 'New' file action.
     */
    @Test
    void testNewFileAction() {
        noteTextArea.setText("Some text");
        fileMenuHandler.createFileMenu().getItem(1).doClick(); 
        assertEquals("", noteTextArea.getText(), "Text area should be empty after 'New' action.");
    }

    /**
     * Tests the 'Print to Printer' action.
     */
    @Test
    void testPrintToPrinterAction() {
        fileMenuHandler.printNoteToPrinter();
        assertTrue(outContent.toString().contains("Printing to Printer"), "Print to Printer action should trigger printing.");
    }

    /**
     * Tests the 'Open File' action.
     */
    @Test
    void testOpenFileAction() {
        JFileChooser mockFileChooser = mock(JFileChooser.class);
        when(mockFileChooser.showOpenDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
        fileMenuHandler.fileChooser = mockFileChooser;

        fileMenuHandler.openFile(); // just close the menu if it shows up properly beacuse it cant be checked
    }

    /**
     * Tests the 'Save File' action.
     */
    @Test
    void testSaveFileAction() {
        // Mock JFileChooser to simulate user specifying a file to save to
        JFileChooser mockFileChooser = mock(JFileChooser.class);
        when(mockFileChooser.showSaveDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
        fileMenuHandler.fileChooser = mockFileChooser;

        // Execute the method to test
        fileMenuHandler.saveFile(); // just close the menu if it shows up properly
    }

    /**
     * Tests the 'Show Print Menu' action.
     */
    @Test
    void testShowPrintMenuAction() {
        // just close the menu if it shows up properly
        Exception exception = null;
        try {
            fileMenuHandler.showPrintMenu();
        } catch (Exception e) {
            exception = e;
        }
        assertNull(exception, "Showing the print menu should not throw an exception.");
    }
}
