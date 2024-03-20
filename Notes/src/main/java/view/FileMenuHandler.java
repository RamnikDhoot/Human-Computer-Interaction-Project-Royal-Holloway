package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The {@code FileMenuHandler} class is responsible for managing file operations
 * in a text editing application.
 * It handles tasks such as opening, saving, printing files, and creating a
 * "File" menu with these options.
 * The class integrates with Swing components to offer a graphical interface for
 * file operations.
 */
public class FileMenuHandler {
    private JTextArea noteTextArea;
    private JFrame frame;
    public JFileChooser fileChooser;

    /**
     * Constructs a {@code FileMenuHandler} with references to the main application
     * window
     * and the JTextArea where documents are edited. Initializes a
     * {@code JFileChooser} for
     * file operations.
     *
     * @param frame        The JFrame representing the main application window.
     * @param noteTextArea The JTextArea used for editing text documents.
     */
    public FileMenuHandler(JFrame frame, JTextArea noteTextArea) {
        this.frame = frame;
        this.noteTextArea = noteTextArea;
        this.fileChooser = new JFileChooser();
    }

    /**
     * Creates and returns a JMenuItem with the specified name and ActionListener.
     * This utility method simplifies the creation of menu items for the file menu.
     *
     * @param name           The text of the menu item.
     * @param actionListener The ActionListener to respond to menu item actions.
     * @return A JMenuItem configured with the specified text and action listener.
     */
    private JMenuItem createMenuItem(String name, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(actionListener);
        return menuItem;
    }

    /**
     * Creates and configures the "File" menu for the application. Adds menu items
     * for
     * printing, creating new documents, opening, saving, and exiting the
     * application.
     *
     * @return A JMenu configured with options for file operations.
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
     * Displays a dialog with options for printing the current document. Options
     * include
     * direct printing to a printer or viewing a print preview.
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
     * Prints the current document to a printer. This placeholder method
     * demonstrates
     * where printer integration would be implemented.
     */
    public void printNoteToPrinter() {
        // Empty as its just for demonstration

        System.out.println("Printing to Printer");
    }

    /**
     * Displays a print preview of the current document. This method demonstrates
     * the
     * integration of a print preview feature.
     */
    public void showPrintPreview() {
        PrintPreviewExample printPreview = new PrintPreviewExample(frame);
        printPreview.displayPrintPreview();
    }

    /**
     * Opens a file chooser dialog to select and open a file. The contents of the
     * selected
     * file are loaded into the JTextArea for editing.
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
     * document's
     * content to a file. This method facilitates saving the edited text to the
     * filesystem.
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
