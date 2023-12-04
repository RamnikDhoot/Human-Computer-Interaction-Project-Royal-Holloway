package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

public class NotesApplication {

    private final JFrame frame;
    private final List<String> notes;
    private final JTextArea noteTextArea;

    public NotesApplication() {
        frame = new JFrame("Notes Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

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
        JMenuBar mainMenuBar = createMainMenuBar();

        // Set main menu bar to the frame
        frame.setJMenuBar(mainMenuBar);

        // Add components to the frame
        frame.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        frame.add(addNoteButton, BorderLayout.SOUTH);

        // Create formatting menu bar
        JMenuBar formattingMenuBar = createFormattingMenuBar();

        // Add formatting menu bar to the frame
        frame.add(formattingMenuBar, BorderLayout.NORTH);
    }

    private JMenuBar createMainMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Create menus for the main menu bar
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu homeMenu = new JMenu("Home");
        JMenu helpMenu = new JMenu("Help");

        // Add menus to the main menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(homeMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private JMenuBar createFormattingMenuBar() {
        JMenuBar formattingMenuBar = new JMenuBar();

        // Create menus for the formatting menu bar
        JMenu formatMenu = new JMenu("Format");
        JMenuItem fontColorMenuItem = new JMenuItem("Font Color");
        fontColorMenuItem.addActionListener(e -> showFontColorDialog());
        formatMenu.add(fontColorMenuItem);

        // Add formatting menu to the formatting menu bar
        formattingMenuBar.add(formatMenu);

        return formattingMenuBar;
    }

    private void showFontColorDialog() {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose Font Color", Color.BLACK);
        if (selectedColor != null) {
            noteTextArea.setForeground(selectedColor);
        }
    }

    private void addNoteToList(String note) {
        notes.add(note);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotesApplication().getFrame().setVisible(true);
        });
    }
}
