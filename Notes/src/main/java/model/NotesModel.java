package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The NotesModel class is responsible for managing the data of the NotesApplication.
 * It encapsulates operations for adding, removing, and retrieving notes.
 */
public class NotesModel implements Subject{
    private static NotesModel instance; // Singleton instance
    private List<String> notes;
    private List<Observer> observers = new ArrayList<>();

    public NotesModel() {
        notes = new ArrayList<>();
    }

    public static synchronized NotesModel getInstance() {
        if (instance == null) {
            instance = new NotesModel();
        }
        return instance;
    }

    @Override
    public void attach(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addNote(String note) {
        if (note != null && !note.trim().isEmpty()) {
            notes.add(note.trim());
            notifyObservers(); // Notify observers about the change
        }
    }

    /**
     * Removes a note from the model.
     *
     * @param index The index of the note to be removed.
     */
    public void removeNote(int index) {
        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
            notifyObservers(); // Notify observers about the change
        }
    }

    public List<String> getNotes() {
        return new ArrayList<>(notes);
    }// Return a copy of the notes list

    /**
     * Clears all the notes from the model.
     */
    public void clearNotes() {
        if (!notes.isEmpty()) {
            notes.clear();
            notifyObservers(); // Notify observers about the change
        }
    }
}
