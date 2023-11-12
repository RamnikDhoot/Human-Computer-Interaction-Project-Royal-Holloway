package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotesApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Notes");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(600, 400);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = createHighlightedMenu("File");
        JMenu editMenu = createHighlightedMenu("Edit");
        JMenu viewMenu = createHighlightedMenu("View");
        JMenu homeMenu = createHighlightedMenu("Home");
        JMenu helpMenu = createHighlightedMenu("Help");

        JMenuItem newMenuItem = createHighlightedMenuItem("New");
        JMenuItem openMenuItem = createHighlightedMenuItem("Open");
        JMenuItem saveMenuItem = createHighlightedMenuItem("Save");
        JMenuItem exitMenuItem = createHighlightedMenuItem("Exit");

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(homeMenu);
        menuBar.add(helpMenu);
        menuBar.add(Box.createHorizontalGlue());

        frame.setJMenuBar(menuBar);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(20, 30, 20, 30, Color.LIGHT_GRAY), // Adjust thickness here
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Set padding inside the borders
        ));

        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static JMenu createHighlightedMenu(String text) {
        JMenu menu = new JMenu(text);
        menu.addMouseListener(new HighlightMouseListener(menu));
        return menu;
    }

    public static JMenuItem createHighlightedMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addMouseListener(new HighlightMouseListener(menuItem));
        menuItem.setPreferredSize(new Dimension(80, menuItem.getPreferredSize().height));
        return menuItem;
    }

    public static class HighlightMouseListener extends MouseAdapter {
        private final Component component;
        private final Color originalColor;

        public HighlightMouseListener(Component component) {
            this.component = component;
            this.originalColor = component.getBackground();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            component.setBackground(Color.LIGHT_GRAY.brighter());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            component.setBackground(originalColor);
        }
    }
}
