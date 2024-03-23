package view;

import controller.NotesController;
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
import java.util.HashMap;
import java.util.List;
import model.Observer;

/**
 * The {@code NotesView} class implements the Observer interface and serves as
 * the graphical user interface for a notes application.
 * It provides functionalities for creating, displaying, and managing notes
 * through a user-friendly interface.
 */
public class NotesView implements Observer {
    private JFrame frame;

    /**
     * Text area where the content of the current note is displayed and can be edited by the user.
     */
    public JTextArea noteTextArea;

    /**
     * Button to add a new note to the application.
     */
    public JButton addNoteButton;
    private JButton exitTouchScreenModeButton;
    private JMenuBar mainMenuBar;

    /**
     * Flag indicating whether the application is in full-screen mode.
     */
    public boolean isFullScreen = false;
    private Font originalTextAreaFont;
    private ToolBarHandler toolBarHandler;
    private JScrollPane scrollPane;
    private NotesController controller;

    /**
     * Sets the controller for this view. This allows the view to communicate with
     * the
     * controller when actions occur in the GUI. It also attaches this view as an
     * observer
     * to the model through the controller.
     *
     * @param controller The {@code NotesController} that controls the application
     *                   logic.
     */
    public void setController(NotesController controller) {
        this.controller = controller;
        this.controller.getModel().attach(this); // Attach this view as an observer to the model
    }

    /**
     * Constructs the {@code NotesView}. It initializes the user interface components
     * and sets up the necessary interactions.
     */
    public NotesView() {
        initializeUI();
    }

    /**
     * Initializes the user interface of the notes application. It sets up components
     * such as menus, toolbars, buttons, and text areas, and configures the look and
     * feel settings to enhance user experience.
     */
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
        scrollPane = new JScrollPane(noteTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Initialize JButton for adding notes
        addNoteButton = new JButton("Add Note");
        addNoteButton
                .addMouseListener(MaterialUIMovement.getMovement(addNoteButton, MaterialColors.GREEN_400, 7, 1000));
        addNoteButton.setName("addNoteButton"); // Set name after initialization
        addNoteButton.addActionListener(e -> controller.addNote());

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

    /**
     * Initializes toolbars, panels, and other UI components within the application
     * frame.
     * This method sets up the main toolbar with functionality such as listing
     * notes,
     * creating customizable menus, and configuring side panels for additional tools
     * and options.
     * It ensures that all necessary UI elements are properly added to the main
     * application frame
     * and applies a consistent visual style across all components.
     */
    private void setupToolbarsAndPanels() {
        // Create Tool Bar
        toolBarHandler = new ToolBarHandler(frame, noteTextArea);
        toolBarHandler.setExitTouchScreenModeButton(exitTouchScreenModeButton);

        JToolBar toolBar = toolBarHandler.createToolBar();
        JButton listNotesButton = new JButton("List Notes");
        listNotesButton.addActionListener(e -> displayNotesDialog());
        toolBar.add(listNotesButton);

        // Create customizable menu
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

    /**
     * Creates the main menu bar for the application, incorporating various menus
     * including File, Edit,
     * View, Home, Help, Customization, and Advanced. Each menu is populated with
     * relevant menu items
     * and their associated action listeners, providing a comprehensive set of
     * options for the user.
     *
     * @return JMenuBar The fully constructed main menu bar ready to be added to the
     *         application frame.
     */
    private JMenuBar createMenuBar() {
        // Create main menu bar
        JMenuBar menuBar = new JMenuBar();

        // Item 1
        FileMenuHandler fileMenuHandler = new FileMenuHandler(frame, noteTextArea);
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

        lightThemeItem.addActionListener(e -> applyTheme(new mdlaf.themes.MaterialLiteTheme())); // Apply the
        // MaterialLiteTheme
        // when lightThemeItem
        // is pressed
        darkThemeItem.addActionListener(e -> applyTheme(new mdlaf.themes.MaterialOceanicTheme()));
        customThemeItem.addActionListener(e -> showThemeSelectionDialog());

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

    /**
     * Makes the application frame visible to the user. This method is intended to
     * be called
     * after all UI components have been initialized and added to the frame. It
     * ensures that
     * the frame is properly displayed on the screen.
     */
    public void display() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    /**
     * Registers an ActionListener for the "Add Note" button. This listener is
     * triggered when the user
     * interacts with the button, allowing for custom behavior such as opening a
     * dialog to add a new note.
     *
     * @param listener The ActionListener to be attached to the "Add Note" button.
     */
    public void setAddNoteActionListener(ActionListener listener) {
        addNoteButton.addActionListener(listener);
    }

    /**
     * Registers an ActionListener for the button that exits touch screen mode. This
     * listener
     * is responsible for handling the transition from touch screen mode back to the
     * standard
     * interface mode.
     *
     * @param listener The ActionListener to be attached to the exit touch screen
     *                 mode button.
     */
    public void setExitTouchScreenModeActionListener(ActionListener listener) {
        exitTouchScreenModeButton.addActionListener(listener);
    }

    /**
     * Retrieves the current text from the note text area.
     *
     * @return String The text currently contained in the note text area.
     */
    public String getNoteText() {
        return noteTextArea.getText();
    }

    /**
     * Clears the text from the note text area, resetting it to an empty state.
     */
    public void clearNoteText() {
        noteTextArea.setText(""); // Clear the text area after adding a note
    }

    /**
     * Disables touch-screen mode by reverting UI elements to their original sizes
     * for normal mouse and keyboard interaction.
     * This includes resetting the font size of the JTextArea to its original size
     * and hiding the exit touch screen mode button.
     */
    public void disableTouchScreenMode() {
        // Hide the exit touch screen mode button
        exitTouchScreenModeButton.setVisible(false);
        // Reset the font size of the JTextArea to its original size
        noteTextArea.setFont(originalTextAreaFont);

        // Reset the font size of menu items
        for (int i = 0; i < frame.getJMenuBar().getMenuCount(); i++) {
            JMenu menu = frame.getJMenuBar().getMenu(i);
            changeMenuFontSize(menu, originalTextAreaFont.getSize());
        }

        // Revalidate and repaint the frame to ensure all components are updated
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Displays a list of notes in the note text area. This method is useful for
     * showing multiple notes
     * at once, separated by new lines.
     *
     * @param notes A list of strings, each representing a note to be displayed.
     */
    public void displayNotes(List<String> notes) {
        // Updates the text area with a list of notes
        noteTextArea.setText(String.join("\n", notes));
    }

    /**
     * Enables touch-screen mode by enlarging UI elements and adjusting their
     * visibility for easier
     * interaction on touch-based devices. This mode enhances accessibility and
     * usability in touch screen environments.
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
        // Optionally, display an on-screen keyboard
        JDialog keyboardDialog = createKeyboardDialog(frame);
        keyboardDialog.setLocationRelativeTo(frame);
        keyboardDialog.setVisible(true);
    }

    /**
     * Toggles the application window between full-screen and windowed mode. This
     * method allows the user
     * to switch between different display modes for their preference or
     * convenience.
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

    /**
     * Provides access to the main application frame.
     *
     * @return JFrame The main application frame.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Recursively changes the font size of menu items and their submenus to enhance
     * readability
     * or fit the user's preference. This method is particularly useful when
     * adjusting UI elements
     * for touch screen mode or for accessibility reasons.
     *
     * @param menu The menu for which to adjust font size.
     * @param size The new font size to apply to the menu and its items.
     */
    private void changeMenuFontSize(JMenu menu, int size) {
        menu.setFont(new Font("Arial", Font.BOLD, size)); // Set the menu font size
        for (int i = 0; i < menu.getItemCount(); i++) {
            JMenuItem menuItem = menu.getItem(i);
            if (menuItem instanceof JMenu) { // Check if the menuItem is a submenu (a nested menu)
                changeMenuFontSize((JMenu) menuItem, size);
            } else if (menuItem != null) { // Check to avoid null pointer exception
                menuItem.setFont(new Font("Arial", Font.PLAIN, size));
            }
        }
    }

    /**
     * Creates an on-screen keyboard dialog, providing an alternative input method
     * for users.
     * This dialog features a customizable keyboard layout and predictive text
     * capabilities, enhancing
     * accessibility and user experience in touch screen or keyboard-less
     * environments.
     *
     * @param parent The JFrame parent of the dialog, typically the main application
     *               frame.
     * @return A JDialog object representing the on-screen keyboard.
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
        String[] predictiveTexts = { "hello", "world", "Java", "Swing" };
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
     * Displays a dialog containing all notes in a scrollable text area. This method
     * provides a convenient
     * way to view all notes at once, enhancing the user's ability to browse and
     * manage their notes.
     */
    public void displayNotesDialog() {
        // Ask the controller for the formatted notes
        String allNotes = controller.getFormattedNotes();
        // Display the notes in a dialog
        JTextArea textArea = new JTextArea(allNotes);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(frame, scrollPane, "All Notes", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Applies a Material UI font to all components within a given component tree,
     * enhancing the overall
     * aesthetic and consistency of the application's interface. This method
     * recursively updates the font
     * of each component to ensure a uniform look and feel.
     *
     * @param component The root component from which to start applying the Material
     *                  UI font.
     */
    private void applyMaterialFontToAllComponents(Component component) {
        component.setFont(new Font("Roboto", Font.PLAIN, 14));
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) { // Iterate over each child component
                // within the component container
                applyMaterialFontToAllComponents(child);
            }
        }
    }

    /**
     * Displays a dialog for selecting a font color. The selected color is then
     * applied to the note
     * text area, allowing the user to customize the appearance of their notes.
     */
    private void showFontColorDialog() {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose Font Color", Color.BLACK);
        if (selectedColor != null) {
            noteTextArea.setForeground(selectedColor);// Checks if selected color is valid then changes the text color
        }
    } // Opens menu for changing colors, already installed in swing

    /**
     * Displays a dialog for theme selection, allowing the user to choose from
     * various pre-defined
     * or custom themes to apply to the application's interface. This dialog
     * enhances customization
     * and personalization, improving the user experience.
     */
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
        themePreviews.put("Material Oceanic",
                new ImageIcon(getClass().getResource("/Screenshot 2024-03-16 110015.png")));
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
                ImageIcon resizedIcon = resizeIcon(themePreviews.get(themeName), previewLabel.getWidth(),
                        previewLabel.getHeight());
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

    /**
     * Applies the selected theme to the application, updating the look and feel
     * accordingly. This method also logs the application of the new theme.
     *
     * @param theme the {@code MaterialTheme} object representing the new theme to apply
     */
    public void applyTheme(mdlaf.themes.MaterialTheme theme) {
        try {
            MaterialLookAndFeel lookAndFeel = new MaterialLookAndFeel(theme);
            UIManager.setLookAndFeel(lookAndFeel);
            updateUI(); // Update the UI to apply the theme
            System.out.println(theme.getName() + " Theme applied.");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refreshes the UI to apply any pending updates or changes. This method is
     * typically called
     * after altering the appearance or layout of UI components to ensure that the
     * changes are
     * accurately reflected on the screen.
     */
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

    /**
     * Resizes an ImageIcon to fit specified dimensions, maintaining the aspect
     * ratio. This method
     * is useful for adjusting icons to fit within specific UI components without
     * distorting their
     * appearance.
     *
     * @param icon   The ImageIcon to resize.
     * @param width  The desired width of the icon.
     * @param height The desired height of the icon.
     * @return An ImageIcon resized to the specified dimensions.
     */
    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Notifies the UI to update based on changes in the underlying model. This method
     * ensures that the view reflects the current state of the application, such as
     * displaying the latest notes or applying new settings.
     */
    @Override
    public void update() {
        // Update the view to reflect changes in the model
        displayNotes(controller.getModel().getNotes());
    }

}