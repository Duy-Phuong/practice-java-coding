package stream;

import java.util.Arrays;
import java.util.List;

public class TestStreamFindFirst {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer any = list.stream().filter(x -> "a".equalsIgnoreCase(null)).findFirst().orElseGet(() -> 1);
//        if (any.isPresent()) {
//            Integer result = any.get();
            System.out.println(any);
//        }
    }
}
