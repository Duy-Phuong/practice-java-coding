package example.textarea.fonts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class FontMetricsExample extends JPanel {
    public void paint(Graphics g) {
        String msg = "Tutorials Point";
        Font f = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15);
        FontMetrics fm = getFontMetrics(f);
        g.setFont(f);
        int x = (getSize().width - fm.stringWidth(msg)) / 2;
        System.out.println("x= " + x);
        int y = getSize().height / 2;
        System.out.println("y= " + y);
        g.drawString(msg, x, y);
    }

    public static void main(String args[]) {
        JFrame test = new JFrame();
        test.getContentPane().add(new FontMetricsExample());
        test.setTitle("FontMetrics Test");
        test.setSize(350, 275);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setLocationRelativeTo(null);
        test.setVisible(true);
    }
}
