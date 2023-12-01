package com.example;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NotesApplication {

    private final JFrame frame;
    private final List<String> notes;

    public NotesApplication() {
        frame = new JFrame("Notes Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        notes = new ArrayList<>();

        // Create components
        JTextArea noteTextArea = new JTextArea();
        JButton addNoteButton = new JButton("Add Note");

        // Set names for components
        noteTextArea.setName("noteTextArea");
        addNoteButton.setName("addNoteButton");

        // Add action listener to the button
        addNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String note = noteTextArea.getText();
                addNoteToList(note);
                noteTextArea.setText(""); // Clear the text area after adding a note
            }
        });

        // Set layout
        frame.setLayout(new BorderLayout());

        // Add components to the frame
        frame.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        frame.add(addNoteButton, BorderLayout.SOUTH);
    }

    public JFrame getFrame() {
        return frame;
    }

    public List<String> getNotes() {
        return notes;
    }

    private void addNoteToList(String note) {
        notes.add(note);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotesApplication().getFrame().setVisible(true);
        });
    }
}
