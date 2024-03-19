package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code NotesModel} class serves as the data management component in the
 * MVC architecture for a note-taking application.
 * It manages the application's notes data and supports operations such as
 * adding, removing, and retrieving notes.
 * Utilizing the Singleton design pattern, it ensures that only one instance of
 * the model exists throughout the application lifecycle.
 * This class also implements the {@code Subject} interface, allowing observers
 * to subscribe to changes in the model state.
 */

public class NotesModel implements Subject {
    private static NotesModel instance; // Singleton instance
    private List<String> notes;
    private List<Observer> observers = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation outside of the
     * {@code getInstance()} method.
     * Initializes the list of notes.
     */
    public NotesModel() {
        notes = new ArrayList<>();
    }

    /**
     * Provides global access to the singleton instance of the {@code NotesModel}.
     * If the instance does not exist, it is created.
     *
     * @return The singleton instance of {@code NotesModel}.
     */
    public static synchronized NotesModel getInstance() {
        if (instance == null) {
            instance = new NotesModel();
        }
        return instance;
    }

    /**
     * Registers an observer to the list of observers.
     * An observer is added only if it is not already registered.
     *
     * @param o The observer to attach.
     */
    @Override
    public void attach(Observer o) {
        if (!observers.contains(o)) { // Ensures the observer is not already in the list before adding it
            observers.add(o);
        }
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param o The observer to detach.
     */
    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    /**
     * Notifies all registered observers of a change in the model's state.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Adds a new note to the model and notifies observers of the change.
     * A note will only be added if it is not null and not empty after trimming.
     *
     * @param note The note to be added.
     */
    public void addNote(String note) {
        if (note != null && !note.trim().isEmpty()) {
            notes.add(note.trim());
            notifyObservers(); // Notify observers about the change
        }
    }

    /**
     * Removes a note at a specified index from the model and notifies observers of
     * the change.
     * The note is removed only if the index is within the valid range of the notes
     * list.
     *
     * @param index The index of the note to be removed.
     */
    public void removeNote(int index) {
        if (index >= 0 && index < notes.size()) {
            notes.remove(index);
            notifyObservers(); // Notify observers about the change
        }
    }

    /**
     * Returns a new list containing all the notes currently managed by the model.
     * This ensures that the internal notes list cannot be directly modified.
     *
     * @return A list of all notes.
     */
    public List<String> getNotes() {
        return new ArrayList<>(notes);
    }// Return a copy of the notes list

    /**
     * Clears all notes from the model and notifies observers of the change.
     */
    public void clearNotes() {
        if (!notes.isEmpty()) {
            notes.clear();
            notifyObservers(); // Notify observers about the change
        }
    }
}
