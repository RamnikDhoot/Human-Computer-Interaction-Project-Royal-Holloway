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

        navigationBar = new JPanel();
        navigationBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        newButton = new JButton("New");
        navigationBar.add(newButton);

        openButton = new JButton("Open");
        navigationBar.add(openButton);

        saveButton = new JButton("Save");
        navigationBar.add(saveButton);

        frame.add(navigationBar, BorderLayout.NORTH);

        pageArea = new JPanel();
        pageArea.setBackground(new Color(245, 245, 220));
        pageArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pageArea.setPreferredSize(new Dimension(600, 400));

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.WHITE);
        backgroundPanel.setLayout(new BorderLayout());

        frame.add(backgroundPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
