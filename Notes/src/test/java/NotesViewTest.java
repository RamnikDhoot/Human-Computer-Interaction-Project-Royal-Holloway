import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import controller.NotesController;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialTheme;
import model.NotesModel;
import view.NotesView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link NotesView} class.
 */
class NotesViewTest {
    private NotesView notesView;
    private NotesController mockedController;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        mockedController = mock(NotesController.class);
        when(mockedController.getModel()).thenReturn(mock(NotesModel.class));
        notesView = new NotesView();
        notesView.setController(mockedController);
    }

    /**
     * Tests if components are initialized properly.
     */
    @Test
    void testComponentsAreInitialized() {
        assertNotNull(notesView.getFrame(), "Frame should be initialized");
        assertNotNull(notesView.noteTextArea, "Note text area should be initialized");
        assertNotNull(notesView.addNoteButton, "Add note button should be initialized");
    }

    /**
     * Tests if adding a note action listener invokes controller's addNote method.
     */
    @Test
    void testAddNoteActionListenerInvokesControllerAddNote() {
    
        // Create a spy on the real controller to check if methods are called
        NotesController controllerSpy = spy(new NotesController(mock(NotesModel.class), notesView));
        notesView.setController(controllerSpy); // https://www.baeldung.com/mockito-spy
    
        notesView.addNoteButton.doClick();
        verify(controllerSpy).addNote();
    }
    
    /**
     * Tests if view is registered with model as observer.
     */
    @Test
    void testViewIsRegisteredWithModelAsObserver() {
        NotesModel model = mockedController.getModel();
        verify(model).attach(notesView); // Verifies the view is registered as an observer
    }

    /**
 * Unit tests for the UI-related functionality of the {@link NotesView} class.
 */
@Test
void testDisplayNotesDialog() {
    // Prepare the controller to return a formatted string of notes
    String expectedNotes = "Note 1\nNote 2";
    when(mockedController.getFormattedNotes()).thenReturn(expectedNotes);

    notesView.displayNotesDialog();
}

/**
 * Tests the application of a theme to the {@link NotesView}.
 */
@Test
void testApplyTheme() {
 
    MaterialTheme theme = new MaterialLiteTheme();
    notesView.applyTheme(theme); 

    LookAndFeel currentLookAndFeel = UIManager.getLookAndFeel();
    assertTrue(currentLookAndFeel instanceof MaterialLookAndFeel);
}

/**
 * Tests toggling full screen mode in the {@link NotesView}.
 */
@Test
void testToggleFullScreen() {
    // isFullScreen starts as false
    assertFalse(notesView.isFullScreen);

    notesView.toggleFullScreen(); // Toggles to true
    assertTrue(notesView.isFullScreen);

    notesView.toggleFullScreen(); // Toggles back to false
    assertFalse(notesView.isFullScreen);
}

/**
 * Tests if the view updates to reflect model changes in the {@link NotesView}.
 */
@Test
void testObserverUpdateReflectsModelChanges() {
    List<String> mockNotes = List.of("Note 1", "Note 2");
    when(mockedController.getModel().getNotes()).thenReturn(mockNotes);

    // Simulate the model notifying observers of a change
    notesView.update();

    // Verify the noteTextArea contains the mock notes
    assertEquals("Note 1\nNote 2", notesView.noteTextArea.getText());
}

}
