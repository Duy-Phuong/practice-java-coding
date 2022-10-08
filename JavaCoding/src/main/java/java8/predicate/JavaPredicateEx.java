package java8.predicate;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class BiggerThanFive<E> implements Predicate<Integer> {

    @Override
    public boolean test(Integer v) {

        Integer five = 5;

        return v > five;
    }
}

public class JavaPredicateEx {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(2, 3, 1, 5, 6, 7, 8, 9, 12);

        BiggerThanFive<Integer> btf = new BiggerThanFive<>();
        nums.stream().filter(btf).forEach(System.out::println);
    }
}