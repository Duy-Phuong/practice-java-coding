package java8.predicate;


import java.time.LocalDate;
import java.time.Month;

public class RangeTest {
    public static void main(String[] args) {
        Range range1 = new Range<>();
        range1.of(new PredicateGeneric<Integer>(5), new PredicateGeneric<Integer>(6));
        System.out.println(range1);

        System.out.println(range1.contains(new PredicateGeneric<>(5)));
        System.out.println(range1.contains(new PredicateGeneric<>(7)));

        Range range2 = new Range<>();
        range1.of(new PredicateGeneric<String>("a"), new PredicateGeneric<String>("c"));
        System.out.println(range1);

        System.out.println(range1.contains(new PredicateGeneric<String>("c")));
        System.out.println(range1.contains(new PredicateGeneric<String>("b")));
        System.out.println(range1.contains(new PredicateGeneric<String>("a")));
        System.out.println(range1.contains(new PredicateGeneric<String>("g")));

        //        Range range2 = Range.of(5, 6);
        //        System.out.println(range2);
        //        System.out.println(range2.contains(6));
        //        System.out.println(range2.contains(5));
        //        System.out.println(range2.contains(7));
    }
}