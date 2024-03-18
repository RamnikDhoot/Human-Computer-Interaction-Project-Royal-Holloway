package view;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import mdlaf.themes.MaterialTheme;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The NotesView class represents the graphical user interface for the notes application.
 */
public class NotesView {
    private JFrame frame;
    private JTextArea noteTextArea;
    private JButton addNoteButton, exitTouchScreenModeButton;
    private JMenuBar mainMenuBar;
    private boolean isFullScreen = false;
    private boolean isTouchScreenMode = false;
    private Font originalTextAreaFont;
    private FileMenuHandler fileMenuHandler;
    private List<String> notes;
    private ToolBarHandler toolBarHandler;
    private JScrollPane scrollPane;





    public NotesView() {
        initializeUI();
    }

    private void initializeUI() {
        try {
            // Set Material look and feel
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
            UIManager.put("Button.mouseHoverEnable", true);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Notes Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // Initialize JTextArea for notes input/display
        noteTextArea = new JTextArea();
        noteTextArea.setBackground(MaterialColors.GRAY_100);
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setName("noteTextArea"); // Set name after initialization

        originalTextAreaFont = noteTextArea.getFont(); // Save original font after JTextArea is initialized

        // Initialize JScrollPane for noteTextArea
        scrollPane = new JScrollPane(noteTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Initialize JButton for adding notes
        addNoteButton = new JButton("Add Note");
        addNoteButton.addMouseListener(MaterialUIMovement.getMovement(addNoteButton, MaterialColors.GREEN_400, 7, 1000));
        addNoteButton.setName("addNoteButton"); // Set name after initialization
        addNoteButton.addActionListener(e -> {
            String note = noteTextArea.getText();
            addNoteToList(note);
            noteTextArea.setText(""); // Clear the text area after adding a note
        });

        // Initialize JButton for exiting touch screen mode
        exitTouchScreenModeButton = new JButton("Exit TS");
        exitTouchScreenModeButton.addActionListener(e -> disableTouchScreenMode());

        // Setup the menu bar
        mainMenuBar = createMenuBar();

        // Setup toolbars and panels
        setupToolbarsAndPanels();

        // Prepare the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupToolbarsAndPanels() {
        toolBarHandler = new ToolBarHandler(frame, noteTextArea);
        toolBarHandler.setExitTouchScreenModeButton(exitTouchScreenModeButton);

        JToolBar toolBar = toolBarHandler.createToolBar();
        JButton listNotesButton = new JButton("List Notes");
        listNotesButton.addActionListener(e -> displayNotesDialog());
        toolBar.add(listNotesButton);

        CustomizableMenu customizableMenu = new CustomizableMenu(frame);
        JToolBar rightToolBar = customizableMenu.createRightToolBar();

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setPreferredSize(new Dimension(100, 0));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.GRAY);
        rightPanel.setPreferredSize(new Dimension(100, 0));

        // Add components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(toolBar, BorderLayout.WEST);

        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(rightToolBar, BorderLayout.EAST);
        frame.add(addNoteButton, BorderLayout.SOUTH);

        frame.setJMenuBar(mainMenuBar);


        applyMaterialFontToAllComponents(frame.getContentPane());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        FileMenuHandler fileMenuHandler = new FileMenuHandler(frame, noteTextArea);
        JMenu fileMenu = fileMenuHandler.createFileMenu();

        JMenu editMenu = new JMenu("Edit");
        JMenuItem fontColorMenuItem = new JMenuItem("Font Color");
        fontColorMenuItem.addActionListener(e -> showFontColorDialog());
        editMenu.add(fontColorMenuItem);

        JMenu viewMenu = new JMenu("View");
        JMenuItem touchScreenModeMenuItem = new JMenuItem("Touch Screen Mode");
        touchScreenModeMenuItem.setBackground(MaterialColors.LIGHT_BLUE_A400);
        touchScreenModeMenuItem.addActionListener(e -> enableTouchScreenMode());
        viewMenu.add(touchScreenModeMenuItem);

        JMenuItem fullScreenMenuItem = new JMenuItem("Full Screen");
        fullScreenMenuItem.addActionListener(e -> toggleFullScreen());
        viewMenu.add(fullScreenMenuItem);

        JMenu homeMenu = new JMenu("Home");
        JMenu helpMenu = new JMenu("Help");

        JMenu customizationMenu = new JMenu("Customization");
        JMenu themesMenu = new JMenu("Themes");
        JMenuItem lightThemeItem = new JMenuItem("Light Theme");
        JMenuItem darkThemeItem = new JMenuItem("Dark Theme");
        JMenuItem customThemeItem = new JMenuItem("Custom Theme");

        lightThemeItem.addActionListener(e -> {});
        darkThemeItem.addActionListener(e -> {});
        customThemeItem.addActionListener(e -> {});

        themesMenu.add(lightThemeItem);
        themesMenu.add(darkThemeItem);
        themesMenu.add(customThemeItem);
        customizationMenu.add(themesMenu);

        JMenu AdvancedMenu = new JMenu("Advanced");
        JMenuItem clearAllNotesItem = new JMenuItem("Clear All Notes");
        AdvancedMenu.add(clearAllNotesItem);

        JMenuItem exportNotesItem = new JMenuItem("Export Notes");
        AdvancedMenu.add(exportNotesItem);

        JMenuItem importNotesItem = new JMenuItem("Import Notes");
        AdvancedMenu.add(importNotesItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(homeMenu);
        menuBar.add(helpMenu);
        menuBar.add(customizationMenu);
        menuBar.add(AdvancedMenu);

        return menuBar;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public void setAddNoteActionListener(ActionListener listener) {
        addNoteButton.addActionListener(listener);
    }

    public void setExitTouchScreenModeActionListener(ActionListener listener) {
        exitTouchScreenModeButton.addActionListener(listener);
    }

    public String getNoteText() {
        return noteTextArea.getText();
    }

    public void clearNoteText() {
        noteTextArea.setText("");
    }

    /**
     * Disables touch-screen mode by reverting UI elements to their original sizes for normal mouse and keyboard interaction.
     * This includes resetting the font size of the JTextArea to its original size and hiding the exit touch screen mode button.
     */
    public void disableTouchScreenMode() {
        // Hide the exit touch screen mode button
        exitTouchScreenModeButton.setVisible(false);
        isTouchScreenMode = false;

        noteTextArea.setFont(originalTextAreaFont);

        for (int i = 0; i < frame.getJMenuBar().getMenuCount(); i++) {
            JMenu menu = frame.getJMenuBar().getMenu(i);
            changeMenuFontSize(menu, 1);
        }
    }

    public void displayNotes(List<String> notes) {
        String allNotes = String.join("\n", notes);
        noteTextArea.setText(allNotes);
    }

    /**
     * Enables touch-screen mode by making UI elements larger for easier tapping and
     * readability.
     */
    public void enableTouchScreenMode() {
        // Show popup message indicating touch screen mode is active
        JOptionPane.showMessageDialog(frame, "Touch Screen Mode Active");

        // Increase font size for JTextArea
        noteTextArea.setFont(new Font("Arial", Font.PLAIN, 24));

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
     * Toggles between full-screen and windowed mode.
     */
    public void toggleFullScreen() {
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
     * Adds a new note to the list of notes.
     *
     * @param note The note to be added.
     */
    void addNoteToList(String note) {
        notes.add(note);
    }

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
     * Displays the font color dialog for selecting text color.
     */
    private void showFontColorDialog() {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose Font Color", Color.BLACK);
        if (selectedColor != null) {
            noteTextArea.setForeground(selectedColor);// Checks if selected color is valid then changes the text color
        }
    } // Opens menu for changing colors, already installed in swing

    private void showThemeSelectionDialog() {
        JDialog themeDialog = new JDialog(frame, "Select Theme", true);
        themeDialog.setLayout(new BorderLayout());
        themeDialog.setSize(new Dimension(600, 400));
        themeDialog.setLocationRelativeTo(frame);

        // Theme names and preview images mapping
        HashMap<String, MaterialTheme> themeMappings = new HashMap<>();
        themeMappings.put("Material Lite", new MaterialLiteTheme());
        themeMappings.put("Material Oceanic", new MaterialOceanicTheme());
        themeMappings.put("JMars Dark", new JMarsDarkTheme());
        themeMappings.put("Abstract", new JMarsDarkTheme());
        themeMappings.put("Blue", new JMarsDarkTheme());
        themeMappings.put("Green", new JMarsDarkTheme());


        HashMap<String, ImageIcon> themePreviews = new HashMap<>();
        themePreviews.put("Material Lite", new ImageIcon(getClass().getResource("/Screenshot 2024-03-16 105855.png")));
        themePreviews.put("Material Oceanic", new ImageIcon(getClass().getResource("/Screenshot 2024-03-16 110015.png")));
        themePreviews.put("JMars Dark", new ImageIcon(getClass().getResource("/dark.png")));


        // Create theme selection panel
        JPanel themeSelectionPanel = new JPanel();
        themeSelectionPanel.setLayout(new BoxLayout(themeSelectionPanel, BoxLayout.Y_AXIS));

        // Create preview panel
        JLabel previewLabel = new JLabel();
        previewLabel.setPreferredSize(new Dimension(10, 10));

        // Current theme name selected
        final String[] currentThemeName = new String[1];

        // Apply button to apply the selected theme
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(e -> {
            MaterialTheme selectedTheme = themeMappings.get(currentThemeName[0]);
            if (selectedTheme != null) {
                applyTheme(selectedTheme); // Apply the selected theme
            }
            themeDialog.dispose(); // Close the dialog
        });

        for (String themeName : themeMappings.keySet()) {
            JButton themeButton = new JButton(themeName);
            themeButton.addActionListener(e -> {
                ImageIcon resizedIcon = resizeIcon(themePreviews.get(themeName), previewLabel.getWidth(), previewLabel.getHeight());
                previewLabel.setIcon(resizedIcon);
                currentThemeName[0] = themeName;
            });
            themeSelectionPanel.add(themeButton);
        }
        themeDialog.add(themeSelectionPanel, BorderLayout.WEST);
        themeDialog.add(previewLabel, BorderLayout.CENTER);
        themeDialog.add(applyButton, BorderLayout.SOUTH);

        themeDialog.setVisible(true);
    }


    private void applyTheme(mdlaf.themes.MaterialTheme theme) {
        try {
            MaterialLookAndFeel lookAndFeel = new MaterialLookAndFeel(theme);
            UIManager.setLookAndFeel(lookAndFeel);
            updateUI(); // Update the UI to apply the theme
            System.out.println(theme.getName() + " Theme applied.");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void updateUI() {
        // Save the current frame size
        Dimension currentSize = frame.getSize();

        // Refresh the UI components to apply the new theme
        SwingUtilities.updateComponentTreeUI(frame);

        // Restore the saved frame size
        frame.setSize(currentSize);
        frame.validate();
        frame.repaint();
    }


    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

}