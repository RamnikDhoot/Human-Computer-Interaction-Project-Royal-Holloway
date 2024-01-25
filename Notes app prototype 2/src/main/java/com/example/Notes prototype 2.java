import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    private JPanel panelMain;
    private JTextField txt;
    private JButton button1;

    public GUI() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button1, txt.getText() + "test");
            }
        });
    }

    public static void main(String[] args) {
        GUI g = new GUI();
        g.setContentPane(g.panelMain);
        g.setTitle("Test title");
//        g.setSize(300,400);
        g.setBounds(600,200,200,200);
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}