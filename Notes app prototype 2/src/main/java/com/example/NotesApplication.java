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

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu fileMenu = new JMenu("File");
        JMenuItem printMenuItem = new JMenuItem("Print");

         // Add action listener to the "Print" menu item
         printMenuItem.addActionListener(e -> showPrintMenu());
        
        fileMenu.add(printMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu homeMenu = new JMenu("Home");
        JMenu helpMenu = new JMenu("Help");

        // Add menus to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(homeMenu);
        menuBar.add(helpMenu);

        // Add "Exit" menu item to "File" menu
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); //Closes program when exit is clicked
            }
        });
        fileMenu.add(exitMenuItem);

        // Set layout
        frame.setLayout(new BorderLayout());

        // Set menu bar to the frame
        frame.setJMenuBar(menuBar);

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

    private void printNoteToPrinter() {
        // Implement the logic to print the note to a printer
        // You can use PrinterJob and NotePrintable as in the previous examples
        System.out.println("Printing to Printer");
    }

    private void showPrintPreview() {
        // Implement the logic to show print preview
        // You can customize this based on your needs
        System.out.println("Show Print Preview");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NotesApplication().getFrame().setVisible(true);
        });
    }
}
