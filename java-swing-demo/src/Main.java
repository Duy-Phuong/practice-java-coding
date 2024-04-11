import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton button1;
    private JPanel myPanel;

    public Main() {
        // Display content in the popup window
        // NOTE: Need to call setContentPane(myPanel); first
        setContentPane(myPanel);

        setTitle("Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = nameTextField.getText();
                JOptionPane.showMessageDialog(Main.this, "Hello " + firstName);
            }
        });
    }
    public static void main(String[] args) {
        new Main();
    }


}