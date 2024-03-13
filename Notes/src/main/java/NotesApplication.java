import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

/**
 * The NotesApplication class represents a simple notes application with GUI
 * using Java Swing.
 * It allows users to add, view, and print notes, as well as toggle between
 * full-screen and touch-screen modes.
 *
 * @author zkac269
 */
public class NotesApplication {

    private final JFrame frame;
    private final List<String> notes;
    private final JTextArea noteTextArea;
    private boolean isFullScreen = false;
    private boolean isTouchScreenMode = false; 
    private JButton exitTouchScreenModeButton; 
    private FileMenuHandler fileMenuHandler;
    private ToolBarHandler toolBarHandler;
    private Font originalTextAreaFont;
    

    /**
     * Constructs a new NotesApplication object with a graphical user interface.
     * Initializes the main frame, components, menus, and toolbar.
     */
    public NotesApplication() {

        try {
            // Set Material look and feel
            UIManager.setLookAndFeel(new MaterialLookAndFeel());

            // You can also customize specific Material UI properties here
            UIManager.put("Button.mouseHoverEnable", true); // for example, to enable button hover effects
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Notes Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        notes = new ArrayList<>();

        // Create components
        noteTextArea = new JTextArea();
        noteTextArea.setBackground(MaterialColors.GRAY_100); // Setting a Material color as background
        JButton addNoteButton = new JButton("Add Note");
        addNoteButton.addMouseListener(MaterialUIMovement.getMovement(addNoteButton, MaterialColors.GREEN_400, 7, 1000));
        // Initialize exit touch screen mode button but do not add it yet
         exitTouchScreenModeButton = new JButton("Exit TS");
         exitTouchScreenModeButton.addActionListener(e -> disableTouchScreenMode());


        // Touch and hold menu (not working)
        // JPopupMenu mockContextMenu = createMockContextMenu();
        // frame.addMouseListener(new MouseAdapter() {
        //     private Timer holdTimer;
    
        //     public void mousePressed(MouseEvent e) {
        //         if (holdTimer == null || !holdTimer.isRunning()) {
        //             holdTimer = new Timer(500, ae -> {
        //                 mockContextMenu.show(frame, e.getX(), e.getY());
        //                 holdTimer.stop();
        //             });
        //             holdTimer.setRepeats(false);
        //             holdTimer.start();
        //         }
        //     }
    
        //     public void mouseReleased(MouseEvent e) {
        //         if (holdTimer != null && holdTimer.isRunning()) {
        //             holdTimer.stop();
        //         }
        //     }
        // });


        // Set names for components
        noteTextArea.setName("noteTextArea");
        addNoteButton.setName("addNoteButton");
        originalTextAreaFont = noteTextArea.getFont();


        // Add action listener to the button
        addNoteButton.addActionListener(e -> {
            String note = noteTextArea.getText();
            addNoteToList(note);
            noteTextArea.setText(""); // Clear the text area after adding a note
        });

        // Create Tool Bar
        toolBarHandler = new ToolBarHandler(frame, noteTextArea);
        toolBarHandler.setExitTouchScreenModeButton(exitTouchScreenModeButton); // Set the exit touchscreen mode button

        JToolBar toolBar = toolBarHandler.createToolBar();

        //adding notes button
        JButton listNotesButton = new JButton("List Notes");
listNotesButton.addActionListener(e -> displayNotesDialog());
toolBar.add(listNotesButton); // Assuming toolBar is accessible here


        // Create customizable menu
        CustomizableMenu customizableMenu = new CustomizableMenu(frame);
        JToolBar rightToolBar = customizableMenu.createRightToolBar();


        // Create main menu bar
        fileMenuHandler = new FileMenuHandler(frame, noteTextArea);

        JMenuBar mainMenuBar = new JMenuBar();

        // Item 1
        // Add the file menu created by FileMenuHandler
        JMenu fileMenu = fileMenuHandler.createFileMenu();

        // Item 2
        JMenu editMenu = new JMenu("Edit");

        // Add "Font Color" option to "Edit" menu
        JMenuItem fontColorMenuItem = new JMenuItem("Font Color");
        fontColorMenuItem.addActionListener(e -> showFontColorDialog());
        editMenu.add(fontColorMenuItem);

        // Item 3
        JMenu viewMenu = new JMenu("View");

        // Add "Touch Screen Mode" option to "View" menu
        JMenuItem touchScreenModeMenuItem = new JMenuItem("Touch Screen Mode");
        touchScreenModeMenuItem.setBackground(MaterialColors.LIGHT_BLUE_A400); // Set a Material color
        touchScreenModeMenuItem.addActionListener(e -> enableTouchScreenMode());
        viewMenu.add(touchScreenModeMenuItem);

        // Add "Full Screen" toggle button to "View" menu
        JMenuItem fullScreenMenuItem = new JMenuItem("Full Screen");
        fullScreenMenuItem.addActionListener(e -> toggleFullScreen());
        viewMenu.add(fullScreenMenuItem);

        // Item 4
        JMenu homeMenu = new JMenu("Home");
        // Item 5
        JMenu helpMenu = new JMenu("Help");

        // Add menus to the main menu bar
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(editMenu);
        mainMenuBar.add(viewMenu);
        mainMenuBar.add(homeMenu);
        mainMenuBar.add(helpMenu);

        // Set layout
        frame.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setPreferredSize(new Dimension(100, 0));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.GRAY);
        rightPanel.setPreferredSize(new Dimension(100, 0));
        // Set main menu bar to the frame
        frame.setJMenuBar(mainMenuBar);

        // Add components to the frame
        frame.add(new JScrollPane(noteTextArea), BorderLayout.CENTER);
        frame.add(addNoteButton, BorderLayout.SOUTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(toolBar, BorderLayout.WEST);
        frame.add(rightToolBar, BorderLayout.EAST);
        applyMaterialFontToAllComponents(frame.getContentPane());

    }

    /**
     * Applies the Material UI font to all components recursively.
     *
     * @param component The root component to start applying the font to.
     */
    private void applyMaterialFontToAllComponents(Component component) {
        component.setFont(new Font("Roboto", Font.PLAIN, 14));
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyMaterialFontToAllComponents(child);
            }
        }
    }

    /**
     * Adds a new note to the list of notes.
     *
     * @param note The note to be added.
     */
    void addNoteToList(String note) {
        notes.add(note);
    }

    /**
     * Displays the font color dialog for selecting text color.
     */
    private void showFontColorDialog() {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose Font Color", Color.BLACK);
        if (selectedColor != null) {
            noteTextArea.setForeground(selectedColor);// Checks if selected color is valid then changes the text color
        }
    } // Opens menu for changing colors, already installed in swing

    /**
     * Enables touch-screen mode by making UI elements larger for easier tapping and
     * readability.
     */
    private void enableTouchScreenMode() {
        // Show popup message indicating touch screen mode is active
        JOptionPane.showMessageDialog(frame, "Touch Screen Mode Active");

        // Increase font size for JTextArea
        noteTextArea.setFont(new Font("Arial", Font.PLAIN, 24));

        // Apply similar changes for other UI elements as needed

        // Adjusting JMenu items font size
        for (int i = 0; i < frame.getJMenuBar().getMenuCount(); i++) {
            JMenu menu = frame.getJMenuBar().getMenu(i);
            changeMenuFontSize(menu, 18);
        }

        // Make the exit button visible
        exitTouchScreenModeButton.setVisible(true);
        isTouchScreenMode = true;

        // Optionally, display an on-screen keyboard
        JDialog keyboardDialog = createKeyboardDialog(frame);
        keyboardDialog.setLocationRelativeTo(frame);
        keyboardDialog.setVisible(true);
    }

    /**
     * Disables touch-screen mode by reverting UI elements to their original sizes for normal mouse and keyboard interaction.
     * This includes resetting the font size of the JTextArea to its original size and hiding the exit touch screen mode button.
     */
    private void disableTouchScreenMode() {
        // Hide the exit touch screen mode button
        exitTouchScreenModeButton.setVisible(false);
        isTouchScreenMode = false;

        noteTextArea.setFont(originalTextAreaFont);

        for (int i = 0; i < frame.getJMenuBar().getMenuCount(); i++) {
            JMenu menu = frame.getJMenuBar().getMenu(i);
            changeMenuFontSize(menu, 1);
        }
    }

    /**
     * Recursively changes the font size of menu items and their submenus.
     *
     * @param menu The menu to adjust font size for.
     */
    private void changeMenuFontSize(JMenu menu, int size) {
        menu.setFont(new Font("Arial", Font.BOLD, size)); // Set the menu font size
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem menuItem = menu.getItem(i);
            if (menuItem instanceof JMenu) {
                changeMenuFontSize((JMenu) menuItem, size);
            } else if (menuItem != null) { // Check to avoid null pointer exception
                menuItem.setFont(new Font("Arial", Font.PLAIN, size));
            }
        }
    }

    /**
     * Creates an on-screen keyboard dialog.
     *
     * @param parent The JFrame parent of the dialog.
     * @return A JDialog object representing the on-screen keyboard dialog.
     */
    private JDialog createKeyboardDialog(JFrame parent) {
        JDialog keyboardDialog = new JDialog(parent, "On-Screen Keyboard", false);
        keyboardDialog.setSize(600, 300);
        keyboardDialog.setLayout(new BorderLayout());
    
        // Predictive Text Panel
        JPanel predictiveTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel predictiveTextLabel = new JLabel("Predictive Text: ");
        predictiveTextPanel.add(predictiveTextLabel);
    
        // Adding some random predictive texts 
        String[] predictiveTexts = {"hello", "world", "Java", "Swing"};
        for (String text : predictiveTexts) {
            JButton predictiveTextButton = new JButton(text);
            predictiveTextPanel.add(predictiveTextButton);
        }
    
        // Keyboard Panel
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(4, 10, 5, 5)); // Adjust grid layout for keys
    
        // Example keyboard layout
        String[] keys = {
            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
            "A", "S", "D", "F", "G", "H", "J", "K", "L", ";",
            "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/",
            "Space", "123", "Lang", "Enter"
        };
    
        for (String key : keys) {
            JButton keyButton = new JButton(key);
            keyboardPanel.add(keyButton);
        }
    
        // Language and Layout Switch Panel
        JPanel switchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton langSwitchButton = new JButton("Lang");
        JButton layoutSwitchButton = new JButton("Layout");
        switchPanel.add(langSwitchButton);
        switchPanel.add(layoutSwitchButton);
    
        // Adding panels to the dialog
        keyboardDialog.add(predictiveTextPanel, BorderLayout.NORTH);
        keyboardDialog.add(keyboardPanel, BorderLayout.CENTER);
        keyboardDialog.add(switchPanel, BorderLayout.SOUTH);
    
        return keyboardDialog;
    }
    
    

    /**
     * Toggles between full-screen and windowed mode.
     */
    private void toggleFullScreen() {
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (isFullScreen) {
            device.setFullScreenWindow(null); // Exit full screen
        } else {
            device.setFullScreenWindow(frame); // Enter full screen
        }
        isFullScreen = !isFullScreen;
    }

    public JFrame getFrame() {
        return frame;
    }

    public List<String> getNotes() {
        return notes;
    }

    // Touch and hold menu (not working)
    // private JPopupMenu createMockContextMenu() {
    //     JPopupMenu contextMenu = new JPopupMenu();
    //     JMenuItem copyItem = new JMenuItem("Copy");
    //     JMenuItem pasteItem = new JMenuItem("Paste");
    //     JMenuItem deleteItem = new JMenuItem("Delete");
    //     JMenuItem formatItem = new JMenuItem("Format");
    
    //     // Add mock actions or simply display them for demonstration
    //     ActionListener mockActionListener = e -> JOptionPane.showMessageDialog(null, "Mock Action Performed: " + e.getActionCommand());
    //     copyItem.addActionListener(mockActionListener);
    //     pasteItem.addActionListener(mockActionListener);
    //     deleteItem.addActionListener(mockActionListener);
    //     formatItem.addActionListener(mockActionListener);
    
    //     contextMenu.add(copyItem);
    //     contextMenu.add(pasteItem);
    //     contextMenu.add(deleteItem);
    //     contextMenu.add(formatItem);
    
    //     return contextMenu;
    // }

    void displayNotesDialog() {
        // Convert the list of notes to a single string, with each note on a new line
        String allNotes = String.join("\n", notes);
        // Display the notes in a dialog
        JTextArea textArea = new JTextArea(allNotes);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Adjust size as needed
        JOptionPane.showMessageDialog(frame, scrollPane, "All Notes", JOptionPane.INFORMATION_MESSAGE);
    }
    

    /**
     * The main method to launch the NotesApplication.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotesApplication().getFrame().setVisible(true));
    }
}
