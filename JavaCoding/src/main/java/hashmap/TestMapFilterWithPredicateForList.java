package hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestMapFilterWithPredicateForList {

    private static Predicate<List<StudentTest>> isBiggerThan18() {
        return studentList -> {
            return studentList.stream()
                    .allMatch(
                            studentEntity -> studentEntity.getStudentAge() > 18);
        };
    }

    public static <K, V> Map<K, V> filterMapWithPredicate(
            Map<K, V> map,
            Predicate<V> predicate) {
        return map.entrySet().stream()
                .filter(entry -> predicate.test(entry.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

    }

    public static void main(String[] args) {
        List<StudentTest> students = new ArrayList<StudentTest>(Arrays.asList(new StudentTest("Phuong", 18)
                , new StudentTest("John", 17)
                , new StudentTest("Jenny", 21)
                , new StudentTest("John", 38)));
        Map<String, List<StudentTest>> studentMap = students.stream().collect(Collectors.groupingBy(StudentTest::getStudentName));
        System.out.println("---------------- GROUP BY ----------------");
        System.out.println(studentMap);
        Map<String, List<StudentTest>> studentMapResult = filterMapWithPredicate(studentMap, isBiggerThan18());
        System.out.println("---------------- FILTER WITH PREDICATE ----------------");
        System.out.println(studentMapResult);
    }

    static class StudentTest {
        String studentName;
        int studentAge;

        public StudentTest(String studentName, int studentAge) {
            this.studentName = studentName;
            this.studentAge = studentAge;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getStudentAge() {
            return studentAge;
        }

        public void setStudentAge(int studentAge) {
            this.studentAge = studentAge;
        }

        @Override
        public String toString() {
            return "StudentTest{" +
                    "studentName='" + studentName + '\'' +
                    ", studentAge=" + studentAge +
                    '}';
        }
    }
}
