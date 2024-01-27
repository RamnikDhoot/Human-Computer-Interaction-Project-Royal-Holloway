import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.NotesApplication;
import com.example.FileMenuHandler;
import javax.swing.*;
import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FileMenuHandlerTest {

    private FrameFixture frame;
    private NotesApplication notesApp;

    @BeforeEach
    void setUp() {
        // Initialize the application and GUI iside of the Event Dispatch Thread
        GuiActionRunner.execute(() -> {
            notesApp = new NotesApplication();
            frame = new FrameFixture(notesApp.getFrame());
        });

        // Show the frame
        frame.show();
    }

    @AfterEach
    void tearDown() {
        // Clean up resources, close the frame
        frame.cleanUp();
    }

    @Test
    void testPrintMenuItemExistsInFileMenu() {
        // Check if the Print menu item exists in the File menu
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "File");
        assertThat(fileMenu).isNotNull();

        JMenuItem printMenuItem = findMenuItemByName(fileMenu, "Print");
        assertThat(printMenuItem).isNotNull();
}

    @Test
    void testNewMenuItemExists() {
    JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
    assertThat(menuBar).isNotNull();

    JMenu fileMenu = findMenuByName(menuBar, "File");
    assertThat(fileMenu).isNotNull();

    JMenuItem newMenuItem = findMenuItemByName(fileMenu, "New");
    assertThat(newMenuItem).isNotNull();
}

@Test
void testOpenMenuItemExists() {
    JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
    assertThat(menuBar).isNotNull();

    JMenu fileMenu = findMenuByName(menuBar, "File");
    assertThat(fileMenu).isNotNull();

    JMenuItem openMenuItem = findMenuItemByName(fileMenu, "Open");
    assertThat(openMenuItem).isNotNull();
}

@Test
void testSaveMenuItemExists() {
    JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
    assertThat(menuBar).isNotNull();

    JMenu fileMenu = findMenuByName(menuBar, "File");
    assertThat(fileMenu).isNotNull();

    JMenuItem saveMenuItem = findMenuItemByName(fileMenu, "Save");
    assertThat(saveMenuItem).isNotNull();
}

@Test
void testExitMenuItemExists() {
    JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
    assertThat(menuBar).isNotNull();

    JMenu fileMenu = findMenuByName(menuBar, "File");
    assertThat(fileMenu).isNotNull();

    JMenuItem exitMenuItem = findMenuItemByName(fileMenu, "Exit");
    assertThat(exitMenuItem).isNotNull();
}
































    // Helper method to find a menu by name, Itterates through all the items in the
    // menu bar then cecks of the name if that item is euqal to the menuName that is
    // given when this method is called
    private JMenu findMenuByName(JMenuBar menuBar, String menuName) {
        for (int i = 0; i < menuBar.getMenuCount(); i++) {
            JMenu menu = menuBar.getMenu(i);
            if (menu.getText().equals(menuName)) {
                return menu;
            }
        }
        return null;
    } // https://joel-costigliola.github.io/assertj/assertj-swing.html For the assertj
      // methods to do this

    // Helper method to find a menu item by name, does the same thing as the upper
    // menu but goes through the items inside of the file menu instead of the whole
    // menu bar
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
