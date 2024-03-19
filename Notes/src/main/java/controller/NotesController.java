package controller;

import model.NotesModel;
import view.NotesView;

import java.util.List;

/**
 * The NotesController class handles user actions from the NotesView
 * and updates the NotesModel accordingly. It also updates the View when the Model changes.
 */
public class NotesController {
    private NotesModel model;
    private NotesView view;

    public NotesController(NotesModel model, NotesView view) {
        this.model = NotesModel.getInstance(); // Access the singleton instance
        this.view = view;
        attachViewListeners();
        initView();
    }

    private void initView() {
        // Initialize the view from model and it will be updated itself via the Observer
    }

    public void addNote() {
        String noteText = view.getNoteText();
        if (noteText != null && !noteText.trim().isEmpty()) {
            model.addNote(noteText);
            view.clearNoteText();
        }
    }

    public String getFormattedNotes() {
        // Get the notes from the model and format them for display
        List<String> notesList = model.getNotes();
        return String.join("\n", notesList);
    }

    private void attachViewListeners() {
        // Attach listeners to view components to handle user actions
        view.setAddNoteActionListener(e -> {
            addNote();
        });

        view.setExitTouchScreenModeActionListener(e -> {
            view.disableTouchScreenMode();
        });

    }

    private void updateViewFromModel() {
        // Update view based on the model state
        view.displayNotes(model.getNotes());
    }

    public void enableTouchScreenMode() {
        view.enableTouchScreenMode();
    }

    public void toggleFullScreen() {
        view.toggleFullScreen();
    }

    // Method to get the model
    public NotesModel getModel() {
        return model;
    }

}
