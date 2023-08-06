package collection.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortOriginalList {
    public static void main(String[] args) {
        ArrayList<Student> ar = new ArrayList<Student>();
        ar.add(new Student(111, "bbbb", "london"));
        ar.add(new Student(131, "aaaa", "nyc"));
        ar.add(new Student(121, "cccc", "jaipur"));

        System.out.println("Unsorted");
        for (int i=0; i<ar.size(); i++)
            System.out.println(ar.get(i));

        // Sort original list by id
        ar.sort(Comparator.comparingInt(Student::getRollno));

        System.out.println("\nSorted by rollno");
        for (int i=0; i<ar.size(); i++)
            System.out.println(ar.get(i));
    }
}
