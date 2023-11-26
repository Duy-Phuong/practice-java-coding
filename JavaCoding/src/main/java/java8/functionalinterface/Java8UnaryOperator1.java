package java8.functionalinterface;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Java8UnaryOperator1 {

    public static void main(String[] args) {

        Function<Integer, Integer> func = x -> x * 2;

        Integer result = func.apply(2);

        System.out.println(result);         // 4

        UnaryOperator<Integer> func2 = x -> x * 2;

        Integer result2 = func2.apply(2);

        System.out.println(result2);        // 4

    }
}
