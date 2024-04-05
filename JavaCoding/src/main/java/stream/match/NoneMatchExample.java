package stream.match;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// https://beginnersbook.com/2017/11/java-8-stream-nonematch-example/
record StudentTest(int stuId, int stuAge, String stuName) {
    public static List<StudentTest> getStudents(){
        List<StudentTest> list = new ArrayList<>();
        list.add(new StudentTest(11, 28, "Lucy"));
        list.add(new StudentTest(28, 27, "Kiku"));
        list.add(new StudentTest(32, 30, "Dani"));
        list.add(new StudentTest(49, 27, "Steve"));
        return list;
    }
}

public class NoneMatchExample {
    public static void main(String[] args) {
        Predicate<StudentTest> p1 = s -> s.stuName().startsWith("L");
        Predicate<StudentTest> p2 = s -> s.stuAge() < 28 && s.stuName().startsWith("P");
        List<StudentTest> list = StudentTest.getStudents();

        /* noneMatch() method returns true if none of the stream elements matches
         * the given predicate
         */
        /* This will return false because there is a element in the stream that
         * matches the condition specified in the predicate p1.
         * Condition: Student Name should start with letter "L"
         * Stream element matched: Lucy
         */
        boolean b1 = list.stream().noneMatch(p1);
        System.out.println("list.stream().noneMatch(p1): "+b1);

        /* This will return true because none of the elements
         * matches the condition specified in the predicate p2.
         * Condition: Student Name should start with letter "P" and age must be <28
         * Stream element matched: none
         */
        boolean b2 = list.stream().noneMatch(p2);
        System.out.println("list.stream().noneMatch(p2): "+b2);
    }
}
