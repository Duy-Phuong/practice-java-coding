package regex.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherExample {
    public static void main(String[] args)
    {
        // create a REGEX String
        String REGEX = "(.*)(ee)(.*)?";

        // create the string
        // in which you want to search
        String actualString
                = "geeksforgeeks";

        // create a Pattern
        Pattern pattern = Pattern.compile(REGEX);

        // get a matcher object
        Matcher matcher = pattern.matcher(actualString);

        // print values if match found
        boolean matchfound = false;
        while (matcher.find()) {
            System.out.println("found the Regex in text:"
                    + matcher.group()
                    + " starting index:" + matcher.start()
                    + " and ending index:"
                    + matcher.end());
            // found the Regex in text:geeksforgeeks starting index:0 and ending index:13
            matchfound = true;
        }
        if (!matchfound) {
            System.out.println("No match found for Regex.");
        }
    }
}
