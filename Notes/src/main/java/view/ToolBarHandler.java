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

public class ToolBarHandler {
    private JTextArea noteTextArea;
    private JFrame frame;
    private final UndoManager undoManager = new UndoManager();
    private JButton exitTouchScreenModeButton; // Button to exit touch screen mode
    private JButton notebookButton; // Button to display the list of notebooks
    private List<String> notebookList; // A list to store notebook names
    private int currentFontSize = 14; // Default font size


    public ToolBarHandler(JFrame frame, JTextArea noteTextArea) {
        this.frame = frame;
        this.noteTextArea = noteTextArea;

        this.notebookList = new ArrayList<>(); // Initialize the notebook list
        notebookList.add("Personal");
        notebookList.add("Work");
        notebookList.add("Travel");
    }

    public JToolBar createToolBar() {
        // Create Undo and Redo buttons and add them to a toolbar
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false); // Make the toolbar non-floatable
        noteTextArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));

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

    public void setExitTouchScreenModeButton(JButton button) {
        this.exitTouchScreenModeButton = button;
    }

    public void undo() {
        try {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        } catch (CannotUndoException ex) {
            ex.printStackTrace();
        }
    }

    public void redo() {
        try {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        } catch (CannotRedoException ex) {
            ex.printStackTrace();
        }
    }

    void displayNotebookList() {
        // Add a "Create new notebook" option
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

    private MaterialTheme getThemeForNotebook(String notebookName) {
        switch (notebookName) {
            case "Personal":
                return new MaterialLiteTheme();
            case "Work":
                return new MaterialOceanicTheme();
            case "Travel":
                return new JMarsDarkTheme();
            default:
                return new MaterialLiteTheme(); // Default theme
        }
    }

    private void changeTheme(MaterialTheme theme) {
        try {
            MaterialLookAndFeel lookAndFeel = new MaterialLookAndFeel(theme);
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void loadNotebookContents(String notebookName) {
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        noteTextArea.setFont(titleFont);
        // Set the title text
        noteTextArea.setText("Notebook: " + notebookName);
    }

    // Method to change font size of noteTextArea
    private void setFontSize(int fontSize) {
        currentFontSize = fontSize;
        Font currentFont = noteTextArea.getFont();
        Font newFont = new Font(currentFont.getName(), currentFont.getStyle(), fontSize);
        noteTextArea.setFont(newFont);
        // Update label with the current font size
        for (Component comp : frame.getComponents()) {
            if (comp instanceof JLabel) {
                JLabel label = (JLabel) comp;
                if ("Font Size: ".equals(label.getText().split("\\d+")[0])) {
                    label.setText("Font Size: " + fontSize);
                    break;
                }
            }
        }
    }
    

}
