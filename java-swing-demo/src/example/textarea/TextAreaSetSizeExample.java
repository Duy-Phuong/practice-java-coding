package example.textarea;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

public class TextAreaSetSizeExample {
    public TextAreaSetSizeExample() {
        JFrame frame = new JFrame("Demo");
        frame.setBounds(500, 500, 400, 400);

        JTextArea textArea = new JTextArea("This is a text displayed for our example. More content is added in it now. More content is added in it now. We will now wrap this text!!!!!!!!!!!!!!!!!!! ");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(null);
        // x, y = 0 starting from top left
        textArea.setSize(new Dimension(200, 200));

//         x, y is relative position to the frame
//        x – the new x-coordinate of this component
//        y – the new y-coordinate of this component
//        width – the new width of this component
//        height – the new height of this component

//        textArea.setBounds(100, 100, 200, 200);

        textArea.setBackground(Color.decode("#74b9ff"));
        // text color
        textArea.setForeground(Color.GRAY);

        Container container = frame.getContentPane();
        container.setLayout(null);
        container.setBackground(Color.decode("#dfe6e9"));

        container.add(textArea);


//        frame.setSize(300,300);
//        frame.setLayout(new GridLayout(2, 2));
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TextAreaSetSizeExample();
    }
}
