package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Angela", "Aaron", "Bob", "Claire", "David");

        List<String> namesWithA = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(namesWithA);

        // create a list of strings
        List<String> names2 = Arrays.asList(
                "Geek", "GeeksQuiz", "g1", "QA", "Geek2");

        // declare the predicate type as string and use
        // lambda expression to create object
        Predicate<String> p = (s) -> s.startsWith("G");

        // Iterate through the list
        for (String st : names2) {
            // call the test method
            if (p.test(st))
                System.out.println(st);
        }

        Predicate<Integer> pp = (i) -> (i > -10) && (i < 10);
        System.out.println(pp.test(9));
    }
}
