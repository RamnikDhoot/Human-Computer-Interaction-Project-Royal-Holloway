import model.NotesModel;
import model.Observer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

/**
 * Unit tests for the {@link NotesModel} class.
 */
public class NotesModelTest {

    /**
     * Tests the singleton instance of the NotesModel.
     */
    @Test
    void testSingletonInstance() {
        NotesModel firstInstance = NotesModel.getInstance();
        NotesModel secondInstance = NotesModel.getInstance();
        assertSame(firstInstance, secondInstance, "Both instances should be the same object.");
    }


    /**
     * Tests that adding a note notifies observers.
     */
    @Test
    void testAddNoteNotifiesObservers() {
        NotesModel model = NotesModel.getInstance();
        Observer observer = mock(Observer.class);
        model.attach(observer);

        model.addNote("Test Note");

        verify(observer).update(); // Verify observer was notified
    }

    /**
     * Tests removing a note from the NotesModel.
     */
    @Test
    void testRemoveNote() {
        NotesModel model = NotesModel.getInstance();
        model.addNote("Test Note");

        model.removeNote(0);
        assertTrue(model.getNotes().isEmpty(), "The note should be removed.");
    }

    /**
     * Tests that removing a note notifies observers.
     */
    @Test
    void testRemoveNoteNotifiesObservers() {
        NotesModel model = NotesModel.getInstance();
        Observer observer = mock(Observer.class);
        model.attach(observer);
        model.addNote("Test Note");

        model.removeNote(0);

        verify(observer, times(2)).update(); 
    }

    /**
     * Tests clearing all notes from the NotesModel.
     */
    @Test
    void testClearNotes() {
        NotesModel model = NotesModel.getInstance();
        model.addNote("Note 1");
        model.addNote("Note 2");

        model.clearNotes();
        assertTrue(model.getNotes().isEmpty(), "All notes should be cleared.");
    }

    /**
     * Tests that clearing all notes notifies observers.
     */
    @Test
    void testClearNotesNotifiesObservers() {
        NotesModel model = NotesModel.getInstance();
        Observer observer = mock(Observer.class);
        model.attach(observer);
        model.addNote("Note 1");

        model.clearNotes();

        verify(observer, times(2)).update(); // Once for add, once for clear
    }

    /**
     * Tests retrieving all notes from the NotesModel.
     */
    @Test
    void testGetNotes() {
        NotesModel model = NotesModel.getInstance();
        model.addNote("Note 1");
        model.addNote("Note 2");

        List<String> notes = model.getNotes();
        assertEquals(2, notes.size(), "Should return all notes.");
        assertNotSame(notes, model.getNotes(), "Should return a copy of the notes list, not the original list.");
    }
}
