package regex.pattern;

import java.util.regex.Pattern;

public class PatternMatchesExample {
    public static void main(String[] args) {
        // create a REGEX String
        String REGEX = "(.*)(ee)(.*)?";

        // create the string
        // in which you want to search
        String actualString
                = "geeksforgeeks";

        // use matches method to check the match
        boolean matcher = Pattern.matches(REGEX, actualString);

        // print values if match found
        if (matcher) {
            System.out.println("match found for Regex.");
        }
        else {
            System.out.println("No match found for Regex.");
        }
    }
}
