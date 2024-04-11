package example.textarea;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.GridLayout;

public class TextAreaWrapTextExample {
    public TextAreaWrapTextExample() {
        JFrame frame = new JFrame("Demo");
        JTextArea textArea = new JTextArea("This is a text displayed for our example. More content is added in it now. More content is added in it now. We will now wrap this text!!!!!!!!!!!!!!!!!!! ");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textArea.setBackground(Color.decode("#74b9ff"));

        frame.add(textArea);
        frame.setSize(300,300);
        frame.setLayout(new GridLayout(2, 2));
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TextAreaWrapTextExample();
    }
}
