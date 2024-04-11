package example.layout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class FlowLayoutExample2 extends JFrame {
    // Declaration of objects of JLabel class.
    JLabel l1, l2, l3, l4, l5;

    // Constructor of Example class.
    public FlowLayoutExample2()
    {
        // Creating Object of "FlowLayout" class
        FlowLayout layout = new FlowLayout();

        // Creating Object of "FlowLayout" class, passing
        // RIGHT alignment through constructor.
//        FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);

        // this Keyword refers to current object.
        // Function to set Layout of JFrame.
        this.setLayout(layout);

        // Initialization of object "l1" of JLabel class.
        l1 = new JLabel("Label 1  ");

        // Initialization of object "l2" of JLabel class.
        l2 = new JLabel("Label 2  ");

        // Initialization of object "l3" of JLabel class.
        l3 = new JLabel("Label 3  ");

        // Initialization of object "l4" of JLabel class.
        l4 = new JLabel("Label 4  ");

        // Initialization of object "l5" of JLabel class.
        l5 = new JLabel("Label 5  ");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(l2);
        jPanel.add(l3);
//        jPanel.doLayout();

        // this Keyword refers to current object.
        // Adding Jlabel "l1" on JFrame.
        this.add(l1);

        // Adding Jlabel "l2" on JFrame.
//        this.add(l2);

        // Adding Jlabel "l3" on JFrame.
//        this.add(l3);

        this.add(jPanel);

        // Adding Jlabel "l4" on JFrame.
        this.add(l4);

        // Adding Jlabel "l5" on JFrame.
        this.add(l5);
    }
}

    class MainFrame2 {
        // Driver code
        public static void main(String[] args)
        {
            // Creating Object of Example class.
            FlowLayoutExample2 f = new FlowLayoutExample2();

            // Function to set title of JFrame.
            f.setTitle("Example of FlowLayout");

            // Function to set Bounds of JFrame.
            f.pack();

            // Function to set visible status of JFrame.
            f.setVisible(true);

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }