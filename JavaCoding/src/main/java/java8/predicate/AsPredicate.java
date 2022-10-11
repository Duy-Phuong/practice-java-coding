package java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class AsPredicate {
    public static void main(String[] args) {

        List<String> words = Arrays.asList("skylark", "trial", "water", "cloud", "curtain", "falcon");

        Predicate<String> pred = Pattern.compile("^...{3}$").asPredicate();
        boolean res = words.stream().anyMatch(pred);

        if (res) {
            System.out.println("There is a word which has three latin characters");
        } else {
            System.out.println("There is no word which has three latin characters");
        }
    }
}
