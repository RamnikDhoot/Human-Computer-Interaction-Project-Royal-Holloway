import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HamburgerMenuPanel extends JPanel implements ActionListener {

    private JButton line1Button, line2Button, line3Button;

    public HamburgerMenuPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        line1Button = new JButton();
        line1Button.setBackground(Color.BLACK);
        line1Button.setPreferredSize(new Dimension(10, 10));
        add(line1Button);

        line2Button = new JButton();
        line2Button.setBackground(Color.BLACK);
        line2Button.setPreferredSize(new Dimension(10, 10));
        add(line2Button);

        line3Button = new JButton();
        line3Button.setBackground(Color.BLACK);
        line3Button.setPreferredSize(new Dimension(10, 10));
        add(line3Button);

        line1Button.addActionListener(this);
        line2Button.addActionListener(this);
        line3Button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == line1Button) {
            // Handle line 1 button click event
        } else if (e.getSource() == line2Button) {
            // Handle line 2 button click event
        } else if (e.getSource() == line3Button) {
            // Handle line 3 button click event
        }
    }
}
