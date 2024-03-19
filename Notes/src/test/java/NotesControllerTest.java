import controller.NotesController;
import model.NotesModel;
import view.NotesView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link NotesController} class.
 */
class NotesControllerTest {

    private NotesController controller;
    private NotesModel model;
    private NotesView view;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        model = mock(NotesModel.class);
        view = mock(NotesView.class);
        controller = new NotesController(model, view);
    }

    /**
     * Tests the initialization of the view.
     */
    @Test
    void testInitView() {
    }

    /**
     * Tests adding a note with empty input.
     */
    @Test
    void testAddNoteWithEmptyInput() {
        // Set up the view to return an empty note text
        when(view.getNoteText()).thenReturn(" ");

        controller.addNote();

        verify(model, never()).addNote(anyString());
    }

    /**
     * Tests enabling touch screen mode.
     */
    @Test
    void testEnableTouchScreenMode() {
        // Call the method to test
        controller.enableTouchScreenMode();

        // Verify the interaction with the view
        verify(view).enableTouchScreenMode();
    }

    /**
     * Tests toggling full screen mode.
     */
    @Test
    void testToggleFullScreen() {
        // Call the method to test
        controller.toggleFullScreen();

        // Verify the interaction with the view
        verify(view).toggleFullScreen();
    }
}
