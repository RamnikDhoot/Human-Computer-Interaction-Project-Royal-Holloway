import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.io.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void testNewFileAction() {
        noteTextArea.setText("Some text");
        fileMenuHandler.createFileMenu().getItem(1).doClick(); // Assuming New is the second item
        assertEquals("", noteTextArea.getText(), "Text area should be empty after 'New' action.");
    }

    @Test
    void testSaveFileAction() throws IOException {
        // Mock JFileChooser to simulate user action
        JFileChooser mockFileChooser = Mockito.mock(JFileChooser.class);
        when(mockFileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        File tempFile = File.createTempFile("test", ".txt");
        when(mockFileChooser.getSelectedFile()).thenReturn(tempFile);
        fileMenuHandler.fileChooser = mockFileChooser;

        noteTextArea.setText("Test content");
        fileMenuHandler.saveFile();

        BufferedReader reader = new BufferedReader(new FileReader(tempFile));
        String content = reader.readLine();
        reader.close();
        assertEquals("Test content", content, "File content should match the text area.");
        tempFile.deleteOnExit();
    }

    @Test
    void testOpenFileAction() throws IOException {
        // Mock JFileChooser to simulate user action
        JFileChooser mockFileChooser = Mockito.mock(JFileChooser.class);
        when(mockFileChooser.showOpenDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        File tempFile = File.createTempFile("test", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write("Test content");
        writer.close();
        when(mockFileChooser.getSelectedFile()).thenReturn(tempFile);
        fileMenuHandler.fileChooser = mockFileChooser;

        fileMenuHandler.openFile();

        assertEquals("Test content", noteTextArea.getText().trim(), "Text area should contain the file content.");
        tempFile.deleteOnExit();
    }

    @Test
    void testPrintToPrinterAction() {
        fileMenuHandler.printNoteToPrinter();
        assertTrue(outContent.toString().contains("Printing to Printer"), "Print to Printer action should trigger printing.");
    }

    @Test
    void testShowPrintPreviewAction() {
        fileMenuHandler.showPrintPreview();
        assertTrue(outContent.toString().contains("Show Print Preview"), "Show Print Preview action should trigger preview.");
    }

}
