package example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JframeExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Example Frame");
        JButton button = new JButton("Click Me");
        JLabel label = new JLabel("Hello, World!");

        frame.getContentPane().add(button);
        frame.getContentPane().add(label);

        // Adjust the size of the frame to fit its contents
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
