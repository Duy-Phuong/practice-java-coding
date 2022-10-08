package stream;

import java.util.*;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b"));
        System.out.println(list);

        System.out.println(list
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));

        String[][] dataArray = new String[][]{{"a", "b"},
                {"c", "d"}, {"e", "f"}, {"g", "h"}};

        List<String> listOfAllChars = Arrays.stream(dataArray)
                .flatMap(x -> Arrays.stream(x))
                .collect(Collectors.toList());

        System.out.println(listOfAllChars);

        System.out.println("--------------- Optional -----------------");
        System.out.println(Optional
                .of("string")
                .flatMap(s -> Optional.of("STRING")));



//        [[a], [b]]
//        [a, before_each]
//        [a, b, c, d, e, f, g, h]
//        {John=4}
//        4
    }
}
