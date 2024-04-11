package example.layout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class GridLayoutExample1 extends JFrame {

    GridLayoutExample1() {
        // Creating Object P1 of JPanel class
        JPanel p1 = new JPanel();

        // set the layout
        p1.setLayout(new GridLayout(4, 2));

        // Creating Object of "FlowLayout" class
        FlowLayout layout = new FlowLayout();

        // Creating Object P2 of JPanel class
        JPanel p2 = new JPanel();

        // set the layout
        p2.setLayout(layout);

        // Declaration of objects of JLabel class.
        JLabel one, two, three, four;

        // Declaration of objects of JTextField class.
        JTextField tname, tsalary, tcode, tdesig;

        // Declaration of objects of JButton class.
        JButton buttonSave, buttonExit;

        // Initialization of object
        // "one" of JLabel class.
        one = new JLabel("NAME");

        // Initialization of object
        // "tname" of JTextField class.
        tname = new JTextField(20);

        // Initialization of object
        // "two" of JLabel class.
        two = new JLabel("CODE");

        // Initialization of object
        // "tcode" of JTextField class.
        tcode = new JTextField(20);

        // Initialization of object
        // "three" of JLabel class.
        three = new JLabel("DESIGNATION");

        // Initialization of object
        // "tdesig" of JTextField class.
        tdesig = new JTextField(20);

        // Initialization of object
        // "four" of JLabel class.
        four = new JLabel("SALARY");

        // Initialization of object
        // "tsalary" of JTextField class.
        tsalary = new JTextField(20);

        // Initialization of object
        // "buttonsave" of JButton class.
        buttonSave = new JButton("SAVE");

        // Initialization of object
        // "buttonexit" of JButton class.
        buttonExit = new JButton("EXIT");

        // Adding Jlabel "one" on JFrame.
        p1.add(one);

        // Adding JTextField "tname" on JFrame.
        p1.add(tname);

        // Adding Jlabel "two" on JFrame.
        p1.add(two);

        // Adding JTextField "tcode" on JFrame.
        p1.add(tcode);

        // Adding Jlabel "three" on JFrame.
        p1.add(three);

        // Adding JTextField "tdesig" on JFrame.
        p1.add(tdesig);

        // Adding Jlabel "four" on JFrame.
        p1.add(four);

        // Adding JTextField "tsalary" on JFrame.
        p1.add(tsalary);

        // Adding JButton "buttonsave" on JFrame.
        p2.add(buttonSave);

        // Adding JButton "buttonexit" on JFrame.
        p2.add(buttonExit);

        // add the p1 object which
        // refer to the Jpanel
        add(p1, "North");

        // add the p2 object which
        // refer to the Jpanel
        add(p2, "South");

        // Function to set visible
        // status of JFrame.
        setVisible(true);

        // this Keyword refers to current
        // object. Function to set size of JFrame.
        this.setSize(400, 180);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Main Method
    public static void main(String args[])
    {
        // calling the constructor
        new GridLayoutExample1();
    }
}
