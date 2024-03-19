package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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


    private JMenuItem createMenuItem(String name, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    public JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(createMenuItem("Print", e -> showPrintMenu()));
        fileMenu.add(createMenuItem("New", e -> noteTextArea.setText("")));
        fileMenu.add(createMenuItem("Open", e -> openFile()));
        fileMenu.add(createMenuItem("Save", e -> saveFile()));
        fileMenu.add(createMenuItem("Exit", e -> System.exit(0)));

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
