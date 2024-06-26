package java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateNegate {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> isEven = i -> i % 2 == 0;
        // Java 8
//        Predicate<Integer> isOdd = isEven.negate();

        // Java 11
        Predicate<Integer> isOdd = Predicate.not( isEven );

        List<Integer> evenNumbers = list.stream()
                .filter(isEven)
                .collect(Collectors.toList());

        List<Integer> oddNumbers = list.stream()
                .filter(isOdd)
                .collect(Collectors.toList());

        System.out.println(evenNumbers);
        System.out.println(oddNumbers);
    }
}
