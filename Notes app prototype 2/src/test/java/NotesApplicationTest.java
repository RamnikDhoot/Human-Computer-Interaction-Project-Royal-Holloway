import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.NotesApplication;

import javax.swing.*;
import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesApplicationTest {

    private FrameFixture frame;
    private NotesApplication notesApp;

    @BeforeEach
    void setUp() {
        // Initialize the application and GUI in the EDT (Event Dispatch Thread)
        GuiActionRunner.execute(() -> {
            notesApp = new NotesApplication();
            frame = new FrameFixture(notesApp.getFrame());
        });

        // Show the frame
        frame.show();
    }

    @AfterEach
    void tearDown() {
        // Clean up resources
        frame.cleanUp();
    }

    @Test
    void testAddNote() {
        // Given
        String testNote = "This is a test note.";

        // When
        frame.textBox("noteTextArea").enterText(testNote);
        frame.button("addNoteButton").click();

        // Then
        assertThat(notesApp.getNotes()).containsExactly(testNote);
    }
}
