package java8.groupby;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountOccurrencesMap {
    public static void main(String[] args)
    {
        Map<String, Long> freq = Stream.of("A", "B", "A", "C", "A", "C")
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(freq);
    }
}
