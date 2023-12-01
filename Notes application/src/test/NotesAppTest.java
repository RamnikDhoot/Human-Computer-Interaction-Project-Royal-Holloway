package src.test;

import src.NotesApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class NotesAppTest {
    

    @Test
    void createHighlightedMenu() {
        JMenu menu = NotesApp.createHighlightedMenu("TestMenu");
        assertNotNull(menu);
        assertEquals("TestMenu", menu.getText());
    }

    @Test
    void createHighlightedMenuItem() {
        JMenuItem menuItem = NotesApp.createHighlightedMenuItem("TestMenuItem");
        assertNotNull(menuItem);
        assertEquals("TestMenuItem", menuItem.getText());
    }

}
