package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code FileMenuHandler} class manages file operations for a text editing application.
 * It handles actions such as opening, saving, and printing files, as well as creating
 * a "File" menu with these options. This class interacts with Swing components to provide
 * a graphical user interface for file manipulation.
 */
public class FileMenuHandler {
    private JTextArea noteTextArea;
    private JFrame frame;
    /**
     * File chooser dialog allowing users to select files for opening or saving notes.
     */
    public JFileChooser fileChooser;

    /**
     * Constructs a {@code FileMenuHandler} with references to the main application window
     * and the {@code JTextArea} where documents are edited. It initializes a {@code JFileChooser}
     * for handling file operations.
     *
     * @param frame        the {@code JFrame} representing the main application window
     * @param noteTextArea the {@code JTextArea} used for editing text documents
     */
    public FileMenuHandler(JFrame frame, JTextArea noteTextArea) {
        this.frame = frame;
        this.noteTextArea = noteTextArea;
        this.fileChooser = new JFileChooser();
    }

    /**
     * Creates and returns a {@code JMenuItem} with the specified name and {@code ActionListener}.
     * This utility method simplifies the creation of menu items for the file menu.
     *
     * @param name           the text to display on the menu item
     * @param actionListener the {@code ActionListener} to attach to the menu item
     * @return a {@code JMenuItem} configured with the specified text and action listener
     */
    private JMenuItem createMenuItem(String name, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    /**
     * Constructs and configures the "File" menu for the application with menu items for
     * actions such as printing, creating new documents, opening, saving, and exiting the application.
     *
     * @return a {@code JMenu} populated with file operation options
     */
    public JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        fileMenu.add(createMenuItem("Print", e -> showPrintMenu()));
        fileMenu.add(createMenuItem("New", e -> noteTextArea.setText("")));
        fileMenu.add(createMenuItem("Open", e -> openFile()));
        fileMenu.add(createMenuItem("Save", e -> saveFile()));
        fileMenu.add(createMenuItem("Exit", e -> System.exit(0))); // Terminates the application, A status code of 0
        // indicates a successful exit.

        return fileMenu;
    }

    /**
     * Displays a dialog offering print options for the current document, such as direct printing or print preview.
     */
    public void showPrintMenu() {
        JDialog printDialog = new JDialog(frame, "Print Options", true);
        printDialog.setSize(200, 150);
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
     * Initiates the process to print the current document to a printer.
     * Placeholder for actual printer integration.
     */
    public void printNoteToPrinter() {
        // Empty as its just for demonstration

        System.out.println("Printing to Printer");
    }

    /**
     * Displays a print preview of the current document. This method serves as a demonstration of
     * print preview integration.
     */
    public void showPrintPreview() {
        PrintPreviewExample printPreview = new PrintPreviewExample(frame);
        printPreview.displayPrintPreview();
    }

    /**
     * Opens a dialog for the user to choose a file to open. The selected file's contents are then
     * loaded into the {@code JTextArea} for editing.
     */
    public void openFile() {
        fileChooser = new JFileChooser(); // Create a file chooser object
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try (FileReader reader = new FileReader(file)) {
                noteTextArea.read(reader, null); // Read data from a reader into the noteTextArea
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "File cannot be opened", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Opens a file chooser dialog to select a location and save the current
     * document's content to a file. If the file cannot be saved, an error
     * dialog is displayed.
     */
    public void saveFile() {
        fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try (FileWriter writer = new FileWriter(file)) {
                noteTextArea.write(writer); // Send the content of the noteTextArea to a writer object
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "File cannot be saved", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
