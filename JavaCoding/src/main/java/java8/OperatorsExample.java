package java8;

import java.util.Arrays;
import java.util.List;

public class OperatorsExample {
//    Operator interfaces are special cases of a function that receive and return the same value type
    public static void main(String[] args) {
        List<String> names = Arrays.asList("bob", "josh", "megan");

//        First way
//        names.replaceAll(name -> name.toUpperCase());

//        Second way
        names.replaceAll(String::toUpperCase);

        System.out.println(names);

        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);

        int sum = values.stream()
                .reduce(0, (i1, i2) -> i1 + i2);

        System.out.println("Sum: " + sum);

    }
}
