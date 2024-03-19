package controller;

import model.NotesModel;
import view.NotesView;

import java.util.List;

/**
 * The {@code NotesController} class serves as the controller in the MVC pattern
 * for a note-taking application.
 * It mediates between the {@code NotesView} and {@code NotesModel}, handling
 * user actions initiated in the
 * view and updating the model accordingly. It also updates the view in response
 * to changes in the model's state.
 */
public class NotesController {
    private NotesModel model;
    private NotesView view;

    /**
     * Constructs a {@code NotesController} with specified model and view.
     * It initializes the controller by attaching view listeners and setting up the
     * initial view state.
     *
     * @param model The {@code NotesModel} instance for this application.
     * @param view  The {@code NotesView} instance for this application.
     */
    public NotesController(NotesModel model, NotesView view) {
        this.model = NotesModel.getInstance(); // Access the singleton instance
        this.view = view;
        attachViewListeners();
        initView();
    }

    /**
     * Initializes the view based on the model state. This method sets up the
     * initial state of the view
     * when the application starts, ensuring that the view reflects the current
     * model state.
     */
    private void initView() {
        // Initialize the view from model and it will be updated itself via the Observer
    }

    /**
     * Adds a new note to the model based on user input from the view. If the input
     * is not empty,
     * it updates the model and clears the input field in the view.
     */
    public void addNote() {
        String noteText = view.getNoteText();
        // Check if noteText has a value and is not empty after trimming whitespaces
        if (noteText != null && !noteText.trim().isEmpty()) {
            model.addNote(noteText);
            view.clearNoteText();
        }
    }

    /**
     * Formats and returns a string representation of all notes currently stored in
     * the model.
     * The notes are concatenated into a single string, separated by newline
     * characters.
     *
     * @return A formatted string of all notes.
     */
    public String getFormattedNotes() {
        List<String> notesList = model.getNotes();
        return String.join("\n", notesList);
    }

    /**
     * Attaches listeners to the view's components to handle user actions such as
     * adding a note
     * or exiting touch screen mode. This method facilitates interaction between the
     * user and the application.
     */
    private void attachViewListeners() {
        view.setAddNoteActionListener(e -> {
            addNote();
        });

        view.setExitTouchScreenModeActionListener(e -> {
            view.disableTouchScreenMode();
        });

    }

    /**
     * Updates the view to reflect the current state of the model. This method is
     * typically called
     * after the model has changed to ensure the view accurately represents the
     * model's data.
     */
    private void updateViewFromModel() {
        // Not used but I planned to use it
        view.displayNotes(model.getNotes());
    }

    /**
     * Enables touch screen mode in the view. This method provides an interface for
     * the user to
     * interact with the application in a touch screen environment.
     */
    public void enableTouchScreenMode() {
        view.enableTouchScreenMode();
    }

    /**
     * Toggles the full screen mode of the application view. This method allows the
     * user to switch
     * between full screen and windowed mode for the application's interface.
     */
    public void toggleFullScreen() {
        view.toggleFullScreen();
    }

    /**
     * Returns the {@code NotesModel} instance associated with this controller.
     * This method allows access to the model part of the MVC architecture.
     *
     * @return The current {@code NotesModel} instance.
     */
    public NotesModel getModel() {
        return model;
    }

}
