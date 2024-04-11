package example;

import java.awt.Dimension;

public class DimensionExample {
    public static void main(String args[])
    {

        // create dimension
        Dimension d = new Dimension();
        Dimension d1 = new Dimension(20, 30);
        Dimension d2 = new Dimension(d1);

        // set height and width of dimension d
        d.setSize(30, 30);

        // equating dimensions
        System.out.println("Dimension d and d1 " +
                "are equal = " + d.equals(d1));

        System.out.println("Dimension d and d1 " +
                "are equal = " + d1.equals(d2));

        // print hashcode
        System.out.println("Hashcode of Dimension " +
                "d = " + d.hashCode());

        // display dimension
        display(d, "Dimension d");
        display(d1, "Dimension d1");
        display(d2, "Dimension d2");
    }

    // display dimension
    public static void display(Dimension d, String s)
    {
        System.out.println(s +" Height = " + d.getHeight() +
                " Width= " + d.getWidth());
    }
}
