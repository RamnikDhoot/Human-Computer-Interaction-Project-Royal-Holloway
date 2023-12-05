import org.assertj.swing.core.GenericTypeMatcher; //To be used later for other tests
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JPopupMenuFixture;
import org.assertj.swing.fixture.JPopupMenuInvokerFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.NotesApplication;

import javax.swing.*;
import java.awt.*; //To be used later for other tests
import java.awt.print.PrinterException;

import static org.assertj.core.api.Assertions.assertThat;

public class NotesApplicationTest {

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
    void testAddNote() {
        // The note that is written
        String testNote = "This is a test note.";

        // Put the note in the writing area and clicking add note
        frame.textBox("noteTextArea").enterText(testNote);
        frame.button("addNoteButton").click();

    }

    @Test
    void testMenuBarExists() {
        // Check if the menu bar exists
        assertThat(frame.robot().finder().findByType(JMenuBar.class)).isNotNull();
        // The Robot in AssertJ Swing is a test robot that allows you to interact with
        // your Swing components in a GUI test. It finds the menu bar and checks if it
        // exists.
    }
    @Test
    void testExitMenuItemExists() {
        // Check if the Exit menu item exists in the "File" menu
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "File");
        assertThat(fileMenu).isNotNull();

        JMenuItem exitMenuItem = findMenuItemByName(fileMenu, "Exit");
        assertThat(exitMenuItem).isNotNull();
    }


    @Test
    void testFileMenuExists() {
        // Check if the File menu exists in the menu bar
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "File");
        assertThat(fileMenu).isNotNull();
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
    void testViewMenuExists() {
        // Check if the View menu exists
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "View");
        assertThat(fileMenu).isNotNull();
    }

    @Test
    void testEditMenuExists() {
        // Check if the Edit menu exists
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "Edit");
        assertThat(fileMenu).isNotNull();

    }

    @Test
    void testHomeMenuExists() {
        // Check if the Home menu exists
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu fileMenu = findMenuByName(menuBar, "Home");
        assertThat(fileMenu).isNotNull();

    }

    @Test
    void testHelpMenuExists() {
        // Check if the Help menu exists
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();

        JMenu HelpMenu = findMenuByName(menuBar, "Help");
        assertThat(HelpMenu).isNotNull();

    }

    @Test
    void testFontColorMenuItemExistsInEditMenu() {
        // Check if the Font Color menu item exists in the Edit menu
        JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
        assertThat(menuBar).isNotNull();
    
        JMenu editMenu = findMenuByName(menuBar, "Edit");
        assertThat(editMenu).isNotNull();
    
        JMenuItem fontColorMenuItem = findMenuItemByName(editMenu, "Font Color");
        assertThat(fontColorMenuItem).isNotNull();
    }
    

    

    
    
    

    
    

//     @Test
// void testPrintOptionsExistInFileMenu() {
//     // Check if the Print menu item exists in the File menu
//     JMenuBar menuBar = frame.robot().finder().findByType(JMenuBar.class);
//     assertThat(menuBar).isNotNull();

//     JMenu fileMenu = findMenuByName(menuBar, "File");
//     assertThat(fileMenu).isNotNull();

//     JMenuItem printMenuItem = findMenuItemByName(fileMenu, "Print");
//     assertThat(printMenuItem).isNotNull();

//     // Click on the "Print" menu item to open the options
//     printMenuItem.doClick();

// } Thest for print menu items not working

    


//Test for colour changing menu

//Test that colour changing works

//Test 




















    

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
