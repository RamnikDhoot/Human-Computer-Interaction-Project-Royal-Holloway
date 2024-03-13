import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class FileMenuHandlerTest {
    private JFrame frame;
    private JTextArea noteTextArea;
    private FileMenuHandler fileMenuHandler;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        noteTextArea = new JTextArea();
        fileMenuHandler = new FileMenuHandler(frame, noteTextArea);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }


    @Test
    void testNewFileAction() {
        noteTextArea.setText("Some text");
        fileMenuHandler.createFileMenu().getItem(1).doClick(); // Assuming New is the second item
        assertEquals("", noteTextArea.getText(), "Text area should be empty after 'New' action.");
    }

    @Test
    void testPrintToPrinterAction() {
        fileMenuHandler.printNoteToPrinter();
        assertTrue(outContent.toString().contains("Printing to Printer"), "Print to Printer action should trigger printing.");
    }

    @Test
    void testOpenFileAction() {
        // Mock JFileChooser to simulate the user interaction
        JFileChooser mockFileChooser = mock(JFileChooser.class);
        when(mockFileChooser.showOpenDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
        fileMenuHandler.fileChooser = mockFileChooser;

        // Trigger the open file action
        fileMenuHandler.openFile();// just close the menu if it shows up properly

    }

    @Test
    void testSaveFileAction() {
        // Mock JFileChooser to simulate user specifying a file to save to
        JFileChooser mockFileChooser = mock(JFileChooser.class);
        when(mockFileChooser.showSaveDialog(any())).thenReturn(JFileChooser.APPROVE_OPTION);
        fileMenuHandler.fileChooser = mockFileChooser;

        // Execute the method to test
        fileMenuHandler.saveFile();// just close the menu if it shows up properly


        }


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
