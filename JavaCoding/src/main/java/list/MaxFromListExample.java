package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxFromListExample {
    public static void main(String[] args) {

        List<Integer> values = new ArrayList<Integer>(Arrays.asList(1, 2, 3));

        Integer maxValue = values.stream().max(Comparator.comparing(v -> v)).get();

        System.out.println(maxValue);

    }
}
