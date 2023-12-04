package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

/**
 * The NotesApplication class represents a simple notes application with GUI using Java Swing.
 * It allows users to add, view, and print notes, as well as toggle between full-screen and touch-screen modes.
 *
 * @author zkac269
 */
public class NotesApplication {

    private final JFrame frame;
    private final List<String> notes;
    private final JTextArea noteTextArea;
    private boolean isFullScreen = false;

    /**
     * Constructs a new NotesApplication object with a graphical user interface.
     * Initializes the main frame, components, menus, and toolbar.
     */
    public NotesApplication() {
        frame = new JFrame("Notes Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        notes = new ArrayList<>();

        // Create components
        noteTextArea = new JTextArea();
        JButton addNoteButton = new JButton("Add Note");

        // Set names for components
        noteTextArea.setName("noteTextArea");
        addNoteButton.setName("addNoteButton");

        // Add action listener to the button
        addNoteButton.addActionListener(e -> {
            String note = noteTextArea.getText();
            addNoteToList(note);
            noteTextArea.setText(""); // Clear the text area after adding a note
        });

        // Create main menu bar
        JMenuBar mainMenuBar = new JMenuBar();

        // Create menus for the main menu bar
        JMenu fileMenu = new JMenu("File");
        JMenuItem printMenuItem = new JMenuItem("Print");
        printMenuItem.addActionListener(e -> showPrintMenu());
        fileMenu.add(printMenuItem);

        JMenu editMenu = new JMenu("Edit");

        // Add "Font Color" option to "Edit" menu
        JMenuItem fontColorMenuItem = new JMenuItem("Font Color");
        fontColorMenuItem.addActionListener(e -> showFontColorDialog());
        editMenu.add(fontColorMenuItem);

        JMenu viewMenu = new JMenu("View");

        // Add "Touch Screen Mode" option to "View" menu
        JMenuItem touchScreenModeMenuItem = new JMenuItem("Touch Screen Mode");
        touchScreenModeMenuItem.addActionListener(e -> enableTouchScreenMode());
        viewMenu.add(touchScreenModeMenuItem);

        JMenu homeMenu = new JMenu("Home");
        JMenu helpMenu = new JMenu("Help");

        // Add "Full Screen" toggle button to "View" menu
        JMenuItem fullScreenMenuItem = new JMenuItem("Full Screen");
        fullScreenMenuItem.addActionListener(e -> toggleFullScreen());
        viewMenu.add(fullScreenMenuItem);

        // Add menus to the main menu bar
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(editMenu);
        mainMenuBar.add(viewMenu);
        mainMenuBar.add(homeMenu);
        mainMenuBar.add(helpMenu);

        // Add "Exit" menu item to "File" menu
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        // Set layout
        frame.setLayout(new BorderLayout());

        // Set main menu bar to the frame
        frame.setJMenuBar(mainMenuBar);

        // Add components to the frame
        frame.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        frame.add(addNoteButton, BorderLayout.SOUTH);

         // Add undo and redo buttons to the side
         frame.add(createSideToolbar(), BorderLayout.WEST);
    }


    /**
     * Creates a side toolbar with undo and redo buttons.
     *
     * @return A JToolBar object containing undo and redo buttons.
     */
    private JToolBar createSideToolbar() {
        JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setFloatable(false); // Make the toolbar non-floatable

        // Create undo button
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undo());
        toolbar.add(undoButton);

        // Create redo button
        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> redo());
        toolbar.add(redoButton);

        return toolbar;
    }

    /**
     * Performs the undo operation (currently empty).
     */
    private void undo() {
        //Empty for now
        System.out.println("Undo");
    }

    /**
     * Performs the redo operation (currently empty).
     */
    private void redo() {
        //Empty for now
        System.out.println("Redo");
    }

    /**
     * Gets the main JFrame of the application.
     *
     * @return The main JFrame of the application.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the list of notes stored in the application.
     *
     * @return A List object containing the notes.
     */    
    public List<String> getNotes() {
        return notes;
    }

    /**
     * Adds a new note to the list of notes.
     *
     * @param note The note to be added.
     */
    private void addNoteToList(String note) {
        notes.add(note);
    }

    /**
     * Displays the print options dialog when the "Print" menu item is selected.
     */
    private void showPrintMenu() {
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
    private void printNoteToPrinter() {
        // Empty for now

        System.out.println("Printing to Printer");
    }

    /**
     * Displays the print preview (currently empty).
     */
    private void showPrintPreview() {
        // Empty for now
        System.out.println("Show Print Preview");
    }


    /**
     * Displays the font color dialog for selecting text color.
     */
    private void showFontColorDialog() {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose Font Color", Color.BLACK);
        if (selectedColor != null) {
            noteTextArea.setForeground(selectedColor);//Checks if selected color is valid then changes the text color
        }
    }    // Opens menu for changing colors, already installed in swing

    /**
     * Enables touch-screen mode by displaying an on-screen keyboard dialog.
     */
    private void enableTouchScreenMode() {
        JDialog keyboardDialog = createKeyboardDialog(frame);
        keyboardDialog.setLocationRelativeTo(frame);
        keyboardDialog.setVisible(true);
    }

    /**
     * Creates an on-screen keyboard dialog.
     *
     * @param parent The JFrame parent of the dialog.
     * @return A JDialog object representing the on-screen keyboard dialog.
     */    
    private JDialog createKeyboardDialog(JFrame parent) {
        JDialog keyboardDialog = new JDialog(parent, "On-Screen Keyboard", true);
        keyboardDialog.setSize(400, 200);
    
        // Letters of the alphabet
        String[] alphabet = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L",
            "Z", "X", "C", "V", "B", "N", "M"
        };
    
        // Set a GridLayout with 4 rows and 7 columns for a 4x7 grid
        keyboardDialog.setLayout(new GridLayout(4, 7));
    
        for (String letter : alphabet) {
            JButton letterButton = new JButton(letter);
            keyboardDialog.add(letterButton);
        }
    
        return keyboardDialog;
    }

    /**
     * Toggles between full-screen and windowed mode.
     */
    private void toggleFullScreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (isFullScreen) {
            device.setFullScreenWindow(null); // Exit full screen
        } else {
            device.setFullScreenWindow(frame); // Enter full screen
        }
        isFullScreen = !isFullScreen;
    }

    /**
     * The main method to launch the NotesApplication.
     *
     * @param args Command-line arguments (not used).
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotesApplication().getFrame().setVisible(true));
    }
}
