package java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class BiggerThanSix<E, T> implements Predicate<T> {

    @Override
    public boolean test(T v) {
        Integer five = 5;
        return (Integer)v > five;
    }
}

public class JavaPredicateEx2 {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(2, 3, 1, 5, 6, 7, 8, 9, 12);

        BiggerThanSix<Integer, Integer> btf = new BiggerThanSix<>();
        nums.stream().filter(btf).forEach(System.out::println);

        System.out.println("----- 2 ------");

        List<Integer> nums2 = Arrays.asList(2, 3, 1, 5, 6, 7, 8, 9, 12);

        Predicate<Integer> btf2 = n -> n > 5;

        nums2.stream().filter(btf2).forEach(System.out::println);
    }
}