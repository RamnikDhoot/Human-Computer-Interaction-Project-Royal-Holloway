package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The NotesModel class is responsible for managing the data of the NotesApplication.
 * It encapsulates operations for adding, removing, and retrieving notes.
 */
public class NotesModel {
    private List<String> notes;

    public NotesModel() {
        this.notes = new ArrayList<>();
    }

    /**
     * Adds a note to the model.
     *
     * @param note The note to be added.
     */
    public void addNote(String note) {
        notes.add(note);
    }

    /**
     * Removes a note from the model.
     *
     * @param index The index of the note to be removed.
     */
    public void removeNote(int index) {
        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
        }
    }

    /**
     * Gets all the notes stored in the model.
     *
     * @return A list of notes.
     */
    public List<String> getNotes() {
        return new ArrayList<>(notes); // Return a copy to maintain encapsulation
    }

    /**
     * Clears all the notes from the model.
     */
    public void clearNotes() {
        notes.clear();
    }
}
