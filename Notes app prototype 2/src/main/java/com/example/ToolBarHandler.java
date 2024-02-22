package com.example;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class ToolBarHandler {
    private JTextArea noteTextArea;
    private JFrame frame;
    private final UndoManager undoManager = new UndoManager();
    private JButton exitTouchScreenModeButton; // Button to exit touch screen mode



    public ToolBarHandler(JFrame frame, JTextArea noteTextArea) {
        this.frame = frame;
        this.noteTextArea = noteTextArea;
    }

    public JToolBar createToolBar(){
        // Create Undo and Redo buttons and add them to a toolbar
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false); // Make the toolbar non-floatable
        noteTextArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));


        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> undo());

        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> redo());
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

    
}
