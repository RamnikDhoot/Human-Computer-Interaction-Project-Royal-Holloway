import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements ActionListener {

    private JFrame frame;
    private JPanel navigationBar, pageArea, sidebarLeft, sidebarRight;
    private JButton newButton, openButton, saveButton;

    public Main() {
        frame = new JFrame("Notes App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create navigation bar
        navigationBar = new JPanel();
        navigationBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        newButton = new JButton("New");
        newButton.addActionListener(this);
        navigationBar.add(newButton);

        openButton = new JButton("Open");
        openButton.addActionListener(this);
        navigationBar.add(openButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        navigationBar.add(saveButton);

        frame.add(navigationBar, BorderLayout.NORTH);

        // Create page area with beige color and white background
        pageArea = new JPanel();
        pageArea.setBackground(new Color(245, 245, 220));
        pageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pageArea.setPreferredSize(new Dimension(600, 400));

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
