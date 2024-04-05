package stream.match;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// https://beginnersbook.com/2017/11/java-8-stream-anymatch-example/

public class AnyMatchExample {
    public static List<StudentTest> getStudents(){
        List<StudentTest> list = new ArrayList<>();
        list.add(new StudentTest(11, 28, "Lucy"));
        list.add(new StudentTest(28, 27, "Kiku"));
        list.add(new StudentTest(32, 30, "Dani"));
        list.add(new StudentTest(49, 27, "Steve"));
        return list;
    }

    public static void main(String[] args) {
        Predicate<StudentTest> p1 = s -> s.stuName().startsWith("S");
        Predicate<StudentTest> p2 = s -> s.stuAge() < 28 && s.stuName().startsWith("Z");
        List<StudentTest> list = getStudents();

        /* anyMatch() method checks whether any Stream element matches
         * the specified predicate
         */
        boolean b3 = list.stream().anyMatch(p1);
        System.out.println(b3);
        boolean b4 = list.stream().anyMatch(p2);
        System.out.println(b4);
    }
}
