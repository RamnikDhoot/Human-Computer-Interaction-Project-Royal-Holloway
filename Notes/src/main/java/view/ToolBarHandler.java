package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import mdlaf.themes.MaterialTheme;

/**
 * The {@code ToolBarHandler} class is responsible for creating and managing the toolbar used in the application.
 * It provides functionality for undo and redo operations, font size adjustments, notebook management, and theme changes.
 */
public class ToolBarHandler {
    private JTextArea noteTextArea;
    private JFrame frame;
    private final UndoManager undoManager = new UndoManager();
    private JButton exitTouchScreenModeButton; // Button to exit touch screen mode
    private JButton notebookButton; // Button to display the list of notebooks
    private List<String> notebookList; // A list to store notebook names
    private int currentFontSize = 14; // Default font size

    /**
     * Constructs a new {@code ToolBarHandler} with the specified frame and text area.
     *
     * @param frame        The main application frame
     * @param noteTextArea The text area where notes are displayed and edited
     */
    public ToolBarHandler(JFrame frame, JTextArea noteTextArea) {
        this.frame = frame;
        this.noteTextArea = noteTextArea;

        this.notebookList = new ArrayList<>(); // Initialize the notebook list
        notebookList.add("Personal");
        notebookList.add("Work");
        notebookList.add("Travel");
    }

    /**
     * Creates and returns a {@code JToolBar} equipped with buttons for undo and redo operations,
     * a font size slider, an autosave toggle, and a notebook selection button.
     *
     * @return The fully configured toolbar
     */
    public JToolBar createToolBar() {
        // Create Undo and Redo buttons and add them to a toolbar
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false); // Make the toolbar non-floatable
        noteTextArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit())); // register a
        // listener that gets
        // notified whenever
        // changes that can
        // be undone/redone
        // are made

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undo());

        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> redo());

        // Create the autosave toggle button
        JToggleButton autosaveToggle = new JToggleButton("Autosave");

        // Font size slider
        JSlider fontSizeSlider = new JSlider(8, 20, currentFontSize);
        fontSizeSlider.setMajorTickSpacing(4);
        fontSizeSlider.setPaintTicks(true);
        fontSizeSlider.setPaintLabels(true);

        fontSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newSize = ((JSlider) e.getSource()).getValue();
                if (newSize != currentFontSize) {
                    setFontSize(newSize);
                }
            }
        });

        // Label for font size
        JLabel fontSizeLabel = new JLabel("Font Size: ");
        fontSizeLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextField fontSizeInput = new JTextField(String.valueOf(currentFontSize), 4);
        fontSizeInput.setHorizontalAlignment(JTextField.CENTER);

        Dimension preferredSize = fontSizeInput.getPreferredSize();
        preferredSize.height = (int) (preferredSize.height * 1);
        fontSizeInput.setPreferredSize(preferredSize);

        fontSizeInput.setMaximumSize(preferredSize);
        toolBar.add(fontSizeLabel);
        toolBar.add(fontSizeInput);

        // Create the notebook button
        notebookButton = new JButton("Notebooks");
        notebookButton.addActionListener(e -> displayNotebookList());

        toolBar.add(notebookButton); // Add the notebook button to the toolbar

        toolBar.add(autosaveToggle);
        toolBar.add(undoButton);
        toolBar.add(redoButton);

        if (exitTouchScreenModeButton != null) {
            toolBar.add(exitTouchScreenModeButton);
            exitTouchScreenModeButton.setVisible(false); // Initially hide the button
        }

        return toolBar;
    }

    /**
     * Sets the button for exiting touch screen mode. This button is not visible until set by this method.
     *
     * @param button The button used to exit touch screen mode
     */
    public void setExitTouchScreenModeButton(JButton button) {
        this.exitTouchScreenModeButton = button;
    }

    /**
     * Performs an undo operation if available.
     */
    public void undo() {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Performs a redo operation if available.
     */
    public void redo() {
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } catch (CannotRedoException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Displays a dialog containing a list of notebooks, allowing the user to select an existing one or create a new one.
     */
    public void displayNotebookList() {
        List<String> options = new ArrayList<>(notebookList);
        options.add("Create new notebook...");

        // Use JOptionPane to display the notebook list in a dialog
        String selectedOption = (String) JOptionPane.showInputDialog(
                frame,
                "Select or create a Notebook:",
                "Notebook List",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options.toArray(),
                options.get(0) // Default selected value
        );

        // Check if user wants to create a new notebook
        if ("Create new notebook...".equals(selectedOption)) {
            String newNotebookName = JOptionPane.showInputDialog(frame, "Notebook name:");
            if (newNotebookName != null && !newNotebookName.trim().isEmpty()) {
                notebookList.add(newNotebookName);
            }
        } else if (selectedOption != null) {
            System.out.println("Selected Notebook: " + selectedOption);
            MaterialTheme selectedTheme = getThemeForNotebook(selectedOption);
            if (selectedTheme != null) {
                changeTheme(selectedTheme); // Change the theme to the selected notebook's theme
            }

            frame.setTitle(selectedOption + " - Notes Application");

            loadNotebookContents(selectedOption);
        }
    }

    /**
     * Retrieves the {@code MaterialTheme} associated with a given notebook name for thematic distinction.
     *
     * @param notebookName The name of the notebook
     * @return The theme associated with the given notebook name
     */
    private MaterialTheme getThemeForNotebook(String notebookName) {
        switch (notebookName) {
            case "Work":
                return new MaterialOceanicTheme();
            case "Travel":
                return new JMarsDarkTheme();
            default:
                return new MaterialLiteTheme(); // Default theme
        }
    }

    /**
     * Applies the specified {@code MaterialTheme} to the application to change its appearance based on the selected notebook.
     *
     * @param theme The Material Theme to apply
     */
    private void changeTheme(MaterialTheme theme) {
        try {
            MaterialLookAndFeel lookAndFeel = new MaterialLookAndFeel(theme);
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the contents associated with the selected notebook into the note text area for editing or review.
     *
     * @param notebookName The name of the notebook whose contents are to be loaded
     */
    private void loadNotebookContents(String notebookName) {
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        noteTextArea.setFont(titleFont);
        // Set the title text
        noteTextArea.setText("Notebook: " + notebookName);
    }

    /**
     * Adjusts the font size of the note text area to the specified size and updates the UI to reflect the change.
     *
     * @param fontSize The new font size to set
     */
    private void setFontSize(int fontSize) {
        currentFontSize = fontSize;
        Font currentFont = noteTextArea.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), fontSize);
        noteTextArea.setFont(newFont);
        // Update label with the current font size
        for (Component comp : frame.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if ("Font Size: ".equals(label.getText().split("\\d+")[0])) { // Check if the label specifies the font
                    // size (starts with "Font Size: ")
                    label.setText("Font Size: " + fontSize);
                    break;
                }
            }
        }
    }

}
