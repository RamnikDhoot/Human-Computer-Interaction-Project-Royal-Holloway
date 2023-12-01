import org.assertj.swing.core.GenericTypeMatcher; //To be used later for other tests
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.NotesApplication;

import javax.swing.*;
import java.awt.*; //To be used later for other tests

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

    @Test
    void testMenuBarExists() {
        // Check if the menu bar exists
        assertThat(frame.robot().finder().findByType(JMenuBar.class)).isNotNull();
        //The Robot in AssertJ Swing is a test robot that allows you to interact with your Swing components in a GUI test. It finds the menu bar and checks if it exists.
    }

    @Test
    void testFileMenuExists() {
        // Check if the "File" menu exists in the menu bar
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "File");
        assertThat(fileMenu).isNotNull();
    }

    @Test
    void testExitMenuItemExists() {
        // Check if the "Exit" menu item exists in the "File" menu
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "File");
        assertThat(fileMenu).isNotNull();

        JMenuItem exitMenuItem = findMenuItemByName(fileMenu, "Exit");
        assertThat(exitMenuItem).isNotNull();
    }





















// Helper method to find a menu by name, Itterates through all the items in the menu bar then cecks of the name if that item is euqal to the menuName that is given when this method is called
private JMenu findMenuByName(JMenuBar menuBar, String menuName) {
    for (int i = 0; i < menuBar.getMenuCount(); i++) {
        JMenu menu = menuBar.getMenu(i);
        if (menu.getText().equals(menuName)) {
            return menu;
        }
    }
    return null;
} //https://joel-costigliola.github.io/assertj/assertj-swing.html For the assertj methods to do this

// Helper method to find a menu item by name, does the same thing as the upper menu but goes through the items inside of the file menu instead of the whole menu bar
private JMenuItem findMenuItemByName(JMenu menu, String menuItemName) {
    for (int i = 0; i < menu.getItemCount(); i++) {
        JMenuItem menuItem = menu.getItem(i);
        if (menuItem.getText().equals(menuItemName)) {
            return menuItem;
        }
    }
    return null;
}
    
}
