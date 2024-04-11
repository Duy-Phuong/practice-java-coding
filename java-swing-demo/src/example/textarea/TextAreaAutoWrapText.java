package example.textarea;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TextAreaAutoWrapText {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextArea Wrap Content Example");
        JTextArea textArea = new JTextArea();

        // Enable line wrap and word wrap
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Set preferred size to null to allow JTextArea to size itself
        textArea.setPreferredSize(null);

        // Sample content for demonstration
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        textArea.setText(content);

        // Get the preferred size and set it to the JTextArea
        textArea.setSize(textArea.getPreferredSize());

        // Get the height
        int height = textArea.getHeight();
        System.out.println("Height: " + height);

        frame.getContentPane().add(textArea);
        // Adjust the size of the frame to fit its contents
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
