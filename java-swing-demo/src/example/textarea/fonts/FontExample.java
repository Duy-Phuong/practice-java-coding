package example.textarea.fonts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class FontExample extends JPanel {
    public void paint(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        g.setColor(Color.blue);
        g.drawString("Welcome to Tutorials Point", 10, 20);
    }
    public static void main(String args[]) {
        JFrame test = new JFrame();
        test.getContentPane().add(new FontExample());
        test.setTitle("Font Test");
        test.setSize(350, 275);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setLocationRelativeTo(null);
        test.setVisible(true);
    }
}
