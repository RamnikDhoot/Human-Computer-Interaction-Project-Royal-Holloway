package com.example;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The NotesApplication class represents a simple notes application with GUI
 * using Java Swing.
 * It allows users to add, view, and print notes, as well as toggle between
 * full-screen and touch-screen modes.
 *
 * @author zkac269
 */
public class NotesApplication {

    private final JFrame frame;
    private final List<String> notes;
    private final JTextArea noteTextArea;
    private boolean isFullScreen = false;
    private FileMenuHandler fileMenuHandler;
    private ToolBarHandler toolBarHandler;



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

        //Create Tool Bar
        toolBarHandler = new ToolBarHandler(frame, noteTextArea);
        JToolBar toolBar = toolBarHandler.createToolBar();

        // Create main menu bar
        fileMenuHandler = new FileMenuHandler(frame, noteTextArea);

        JMenuBar mainMenuBar = new JMenuBar();

        // Item 1
        // Add the file menu created by FileMenuHandler
        JMenu fileMenu = fileMenuHandler.createFileMenu();

        // Item 2
        JMenu editMenu = new JMenu("Edit");

        // Add "Font Color" option to "Edit" menu
        JMenuItem fontColorMenuItem = new JMenuItem("Font Color");
        fontColorMenuItem.addActionListener(e -> showFontColorDialog());
        editMenu.add(fontColorMenuItem);

        // Item 3
        JMenu viewMenu = new JMenu("View");

        // Add "Touch Screen Mode" option to "View" menu
        JMenuItem touchScreenModeMenuItem = new JMenuItem("Touch Screen Mode");
        touchScreenModeMenuItem.addActionListener(e -> enableTouchScreenMode());
        viewMenu.add(touchScreenModeMenuItem);

        // Add "Full Screen" toggle button to "View" menu
        JMenuItem fullScreenMenuItem = new JMenuItem("Full Screen");
        fullScreenMenuItem.addActionListener(e -> toggleFullScreen());
        viewMenu.add(fullScreenMenuItem);

        // Item 4
        JMenu homeMenu = new JMenu("Home");
        // Item 5
        JMenu helpMenu = new JMenu("Help");

        // Add menus to the main menu bar
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(editMenu);
        mainMenuBar.add(viewMenu);
        mainMenuBar.add(homeMenu);
        mainMenuBar.add(helpMenu);

        // Set layout
        frame.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setPreferredSize(new Dimension(100, 0));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.GRAY);
        rightPanel.setPreferredSize(new Dimension(100, 0));
        // Set main menu bar to the frame
        frame.setJMenuBar(mainMenuBar);

        // Add components to the frame
        frame.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        frame.add(addNoteButton, BorderLayout.SOUTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(toolBar, BorderLayout.WEST);


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
     * Displays the font color dialog for selecting text color.
     */
    private void showFontColorDialog() {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose Font Color", Color.BLACK);
        if (selectedColor != null) {
            noteTextArea.setForeground(selectedColor);// Checks if selected color is valid then changes the text color
        }
    } // Opens menu for changing colors, already installed in swing

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

    public JFrame getFrame() {
        return frame;
    }

    public List<String> getNotes() {
        return notes;
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
