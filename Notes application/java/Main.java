import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main implements ActionListener {

    private JFrame frame;
    private JPanel navigationBar, pageArea, sidebarLeft, sidebarRight;
    private JButton newButton, openButton, saveButton;

    public Main() {
        frame = new JFrame("Notes App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create navigation bar
        navigationBar = new JPanel();
        navigationBar.setLayout(new BorderLayout());
        navigationBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add styling to navigation bar
        navigationBar.setBackground(Color.WHITE);
        navigationBar.setForeground(Color.BLACK);
        navigationBar.setFont(new Font("Arial", Font.BOLD, 16));

        newButton = new JButton("New");
        newButton.addActionListener(this);
        navigationBar.add(newButton, BorderLayout.WEST);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        navigationBar.add(openButton, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        navigationBar.add(saveButton, BorderLayout.EAST);

        frame.add(navigationBar, BorderLayout.NORTH);

        // Create page area
        pageArea = new JPanel();
        pageArea.setBackground(new Color(145, 245, 220));
        pageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pageArea.setPreferredSize(new Dimension(600, 400));

        // Add lines to page area
        Graphics g = pageArea.getGraphics();
        for (int i = 0; i < pageArea.getHeight(); i += 10) {
            g.drawLine(0, i, pageArea.getWidth(), i);
        }

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setLayout(new BorderLayout());

        // Add left sidebar
        sidebarLeft = new JPanel();
        sidebarLeft.setBackground(Color.WHITE);
        sidebarLeft.setPreferredSize(new Dimension(20, 400));
        backgroundPanel.add(sidebarLeft, BorderLayout.WEST);

        // Add page area to the center
        backgroundPanel.add(pageArea, BorderLayout.CENTER);

        // Add right sidebar
        sidebarRight = new JPanel();
        sidebarRight.setBackground(Color.WHITE);
        sidebarRight.setPreferredSize(new Dimension(20, 400));
        backgroundPanel.add(sidebarRight, BorderLayout.EAST);

        frame.add(backgroundPanel, BorderLayout.CENTER);
        // Add the hamburger menu panel to the main GUI panel
        frame.add(new HamburgerMenuPanel(), BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newButton) {
            System.out.println("New button clicked");
        } else if (e.getSource() == openButton) {
            System.out.println("Open button clicked");
        } else if (e.getSource() == saveButton) {
            System.out.println("Save button clicked");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
