package java8.predicate;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PatternAsPredicateExample {
    public static void main(String[] args)
    {
        // create a REGEX String
        String REGEX = "ee";

        // create the string
        // in which you want to search
        String actualString
                = "aaeebbeecceeddee";

        // create a Pattern using REGEX
        Pattern pattern
                = Pattern.compile(REGEX);

        // get Predicate Object
        Predicate<String> predicate
                = pattern.asPredicate();

        // check whether predicate match
        // with actualString
        boolean value = predicate
                .test(actualString);

        // print result
        System.out.println("value matched: "
                + value);
    }
}
