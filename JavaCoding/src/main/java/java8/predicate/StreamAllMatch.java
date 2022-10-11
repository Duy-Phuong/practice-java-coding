package java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StreamAllMatch {

    public static void main(String[] args) {

        List<Integer> values1 = Arrays.asList(1, 5, 3, 2, 8, 6, 7);
        List<Integer> values2 = Arrays.asList(1, 5, 3, -2, 8, 0, 9);

        Predicate<Integer> isPositive = e -> e > 0;

        boolean res1 = values1.stream().allMatch(isPositive);

        if (res1) {
            System.out.println("All values of collection values1 are positive");
        } else {
            System.out.println("All values of collection values1 are not positive");
        }

        boolean res2 = values2.stream().allMatch(isPositive);

        if (res2) {
            System.out.println("All values of collection values2 are positive");
        } else {
            System.out.println("All values of collection values2 are not positive");
        }
    }
}