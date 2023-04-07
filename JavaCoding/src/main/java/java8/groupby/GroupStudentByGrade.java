package java8.groupby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student
{
    private String name;
    private String grade;
    private int marks;

    Student(String name, String grade, Integer marks)
    {
        this.name = name;
        this.grade = grade;
        this.marks = marks;
    }

    public String getName() { return name; }
    public String getGrade() { return grade; }
    public int getMarks() { return marks; }

    @Override
    public String toString() { return getName(); }
}

// https://www.techiedelight.com/collectors-groupingby-method-java/
// Java program to group students by grades using `Collectors.groupingBy()`
class GroupStudentByGrade
{
    public static void main(String[] args)
    {
        List<Student> students = Arrays.asList(new Student("Tom", "A+", 90),
                new Student("Lisa", "A+", 98),
                new Student("John", "A", 85),
                new Student("Joe", "A", 80),
                new Student("Jason", "E", 35));

        Map<String, List<Student>> studentsByGrade
                = students.stream()
                .collect(Collectors.groupingBy(Student::getGrade));

        for (Map.Entry<String, List<Student>> entry: studentsByGrade.entrySet()) {
            System.out.println("Students with " + entry.getKey() +
                    " grade are " + entry.getValue());
        }
    }
}