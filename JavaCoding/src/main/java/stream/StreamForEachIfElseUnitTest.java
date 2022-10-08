package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamForEachIfElseUnitTest {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ints.stream().forEach(i -> {
            if (i.intValue() % 2 == 0) {
                System.out.print("even");
            } else {
                System.out.print("odd");
            }
            System.out.print(" ");
        });
        System.out.println("Using stream");

        // Stream
        List<Integer> ints2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Stream<Integer> evenIntegers = ints2.stream()
                .filter(i -> i.intValue() % 2 == 0);
        Stream<Integer> oddIntegers = ints.stream()
                .filter(i -> i.intValue() % 2 != 0);

        evenIntegers.forEach(System.out::println);
        oddIntegers.forEach(System.out::println);
    }
}
