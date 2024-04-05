package java9;

import java.util.List;
import java.util.stream.Stream;

public class BreakForEachExample {
    public static void main(String[] args) {
        Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck")
                .takeWhile(n -> n.length() % 2 != 0)
                .forEach(System.out::println);

        Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck")
                .takeWhile(n -> n.length() >= 0)
                .forEach(System.out::println);

        List<String> result = Stream.of("cat", "dog", "elephant", "fox", "rabbit", "duck")
                .takeWhile(n -> n.length() >= 0).toList();
        System.out.println(result.size() == 6);
    }
}
