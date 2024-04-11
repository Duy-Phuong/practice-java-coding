package example;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class EmptyBorderExample {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Demo");
        JLabel label;
        label = new JLabel("Label with empty border!");
        label.setFont(new Font("Verdana", Font.PLAIN, 16));
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(label);
        frame.setSize(550,350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
