package string;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

public class Concat2Strings {
    public static void main(String... args) {
        System.out.println(Optional.ofNullable(null).isPresent());

        Optional<String> first = Optional.ofNullable(" ");
        Optional<String> second = Optional.ofNullable("Test");
        Optional<String> third = Optional.ofNullable(StringUtils.SPACE);
        Optional<String> fourth = Optional.ofNullable(null);
        Optional<String> result = Stream.of(first, second, third, fourth).flatMap(Optional::stream).reduce(String::concat);
        System.out.println(result.isPresent());
        System.out.println(result.get().trim());
    }
}
