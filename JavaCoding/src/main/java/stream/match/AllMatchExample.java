package stream.match;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//https://beginnersbook.com/2017/11/java-8-stream-allmatch-example/

public class AllMatchExample {
    public static List<StudentTest> getStudents(){
        List<StudentTest> list = new ArrayList<>();
        list.add(new StudentTest(11, 28, "Lucy"));
        list.add(new StudentTest(28, 27, "Tim"));
        list.add(new StudentTest(32, 30, "Daniel"));
        list.add(new StudentTest(49, 27, "Steve"));
        return list;
    }

    public static void main(String[] args) {
        Predicate<StudentTest> p1 = s -> s.stuName().startsWith("A");
        Predicate<StudentTest> p2 = s -> s.stuAge() < 40;
        Predicate<StudentTest> p3 = s -> s.stuAge() < 40 && s.stuName().startsWith("P");
        List<StudentTest> list = getStudents();

        /* allMatch() method returns true if all the elements of stream satisfy the
         * given predicate, else it returns false
         */

        /* This will return false because all student names do not start with "A"
         */
        boolean b1 = list.stream().allMatch(p1);
        System.out.println("list.stream().allMatch(p1): "+b1);

        /* This will return true because all students have age less than 40
         */
        boolean b2 = list.stream().allMatch(p2);
        System.out.println("list.stream().allMatch(p2): "+b2);

        /* This will return false because all the students do not satisfy the predicate:
         * Age must be less than 40 and name starts with letter "P"
         */
        boolean b3 = list.stream().allMatch(p3);
        System.out.println("list.stream().allMatch(p3): "+b3);
    }
}
