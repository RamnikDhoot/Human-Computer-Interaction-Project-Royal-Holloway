package controller;

import model.NotesModel;
import view.NotesView;

/**
 * The NotesController class handles user actions from the NotesView
 * and updates the NotesModel accordingly. It also updates the View when the Model changes.
 */
public class NotesController {
    private NotesModel model;
    private NotesView view;

    public NotesController(NotesModel model, NotesView view) {
        this.model = model;
        this.view = view;
        attachViewListeners();
    }

    private void attachViewListeners() {
        // Attach listeners to view components to handle user actions
        view.setAddNoteActionListener(e -> {
            String note = view.getNoteText();
            model.addNote(note);
            view.clearNoteText();
            updateViewFromModel();
        });

        view.setExitTouchScreenModeActionListener(e -> {
            view.disableTouchScreenMode();
        });

    }

    private void updateViewFromModel() {
        // Update view based on the latest model state
        view.displayNotes(model.getNotes());
    }

    public void enableTouchScreenMode() {
        view.enableTouchScreenMode();
    }

    public void toggleFullScreen() {
        view.toggleFullScreen();
    }

}
