package java8.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectorsPartitioningBy {

    public static void main(String[] args) {

        List<Integer> values = Arrays.asList(3, -1, 2, 4, -1, 1, 2, 3);

        Predicate<Integer> isPositive = e -> e > 0;

        Map<Boolean, List<Integer>> groups = values.stream()
                .collect(Collectors.partitioningBy(isPositive));

        System.out.println(groups.get(true));
        System.out.println(groups.get(false));

        List<List<Integer>> subSets = new ArrayList<>(groups.values());
        System.out.println(subSets);
    }

//    [4, 1, 2, 3]
//            [-3, -1, -2, -1]
//            [[-3, -1, -2, -1], [4, 1, 2, 3]]
}