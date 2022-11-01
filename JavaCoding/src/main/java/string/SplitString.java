package string;
// Java code to illustrate group() method

import java.util.regex.*;

public class SplitString {
    public static void main(String[] args)
    {

        // Get the regex to be checked
        String regex = "([a-zA-Z0-9]+)([\\s]+)([a-zA-Z ]+)([\\s]+)([0-9]+)";

        // Create a pattern from regex
        Pattern pattern
                = Pattern.compile(regex);

        // Get the String to be matched
        String stringToBeMatched
                = "!* UserName10 John Smith 01123 *!";

        // Create a matcher for the input String
        Matcher matcher
                = pattern
                .matcher(stringToBeMatched);

        // Get the current matcher state
        MatchResult result
                = matcher.toMatchResult();
        System.out.println("Current Matcher: "
                + result);


        String stringToBeMatched1
                = "!* UserName10 -.John Smith -01123 *!";

        while (matcher.find()) {
            // Get the group matched using group() method
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            System.out.println(matcher.group(5));
        }


        String s1 = "752f79cd-cb40-4296-910b-bd79c654565d.pdf";
        if (s1.contains(".")) {
            System.out.println("ok");
        }
        String s2 = s1.substring(0, s1.lastIndexOf("."));
        System.out.println(s2);
    }
}
