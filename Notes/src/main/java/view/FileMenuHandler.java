package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles file operations for a text editing application, including opening,
 * saving, and printing files, as well as creating and managing a "File" menu
 * with these options.
 */
public class FileMenuHandler {
    private JTextArea noteTextArea;
    private JFrame frame;
    public JFileChooser fileChooser;

    /**
     * Constructs a FileMenuHandler with references to the main application frame
     * and the text area where documents are edited.
     *
     * @param frame The main application window.
     * @param noteTextArea The text area for document editing.
     */
    public FileMenuHandler(JFrame frame, JTextArea noteTextArea) {
        this.frame = frame;
        this.noteTextArea = noteTextArea;
        this.fileChooser = new JFileChooser();
    }

    /**
     * Creates and returns a "File" menu with menu items for new, open, save, print,
     * and exit actions, along with their respective action listeners.
     *
     * @return The "File" menu component for integration into a menu bar.
     */
    public JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem printMenuItem = new JMenuItem("Print");
        printMenuItem.addActionListener(e -> showPrintMenu());

        JMenuItem newFileItem = new JMenuItem("New");
        newFileItem.addActionListener(e -> noteTextArea.setText(""));

        JMenuItem openFileItem = new JMenuItem("Open");
        openFileItem.addActionListener(e -> openFile());

        JMenuItem saveFileItem = new JMenuItem("Save");
        saveFileItem.addActionListener(e -> saveFile());

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(printMenuItem);
        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(exitItem);

        return fileMenu;
    }



    /**
     * Displays a dialog with options for printing the current document, including
     * printing directly to a printer or viewing a print preview.
     */
    public void showPrintMenu() {
        JDialog printDialog = new JDialog(frame, "Print Options", true);
        printDialog.setSize(200, 100);
        printDialog.setLayout(new FlowLayout());

        JButton printToPrinterButton = new JButton("Print to Printer");
        JButton printPreviewButton = new JButton("Print Preview");

        // Add action listeners to the print options
        printToPrinterButton.addActionListener(e -> printNoteToPrinter());
        printPreviewButton.addActionListener(e -> showPrintPreview());

        // Add buttons to the dialog
        printDialog.add(printToPrinterButton);
        printDialog.add(printPreviewButton);

        // Set dialog location relative to the main frame
        printDialog.setLocationRelativeTo(frame);

        // Make the dialog visible
        printDialog.setVisible(true);
    }

    /**
     * Prints the note to the printer (currently empty).
     */
    public void printNoteToPrinter() {
        // Empty as its just for demonstration

        System.out.println("Printing to Printer");
    }

/**
 * Displays the print preview.
 */
public void showPrintPreview() {
    PrintPreviewExample printPreview = new PrintPreviewExample(frame);
    printPreview.displayPrintPreview();
}

    /**
     * Opens a file chooser dialog to select and open a file, loading its content
     * into the text area for editing.
     */
    public void openFile() {
        fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try (FileReader reader = new FileReader(file)) {
                noteTextArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "File cannot be opened", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a file chooser dialog to select a file location and save the current
     * document's content to the file.
     */
    public void saveFile() {
        fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try (FileWriter writer = new FileWriter(file)) {
                noteTextArea.write(writer);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "File cannot be saved", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
