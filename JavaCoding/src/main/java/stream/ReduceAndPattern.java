package stream;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ReduceAndPattern {

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile("\\d+");
        System.out.println(pattern.matcher("1").matches());
        System.out.println(pattern.matcher("12345").matches());
        System.out.println(pattern.matcher("123456789").matches());
        System.out.println(pattern.matcher(" ").matches());
        System.out.println(pattern.matcher("123 45").matches());
        System.out.println(pattern.matcher("  123456789  ").matches());
        System.out.println(pattern.matcher("  123456789  ").matches());
        System.out.println(Integer.parseInt(""));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result1 = numbers.stream().reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result1);

        int result2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(result2);

        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result3 = letters.stream().reduce("", (partialString, element) -> partialString + element);
        System.out.println(result3);

        String result4 = letters.stream().reduce("", String::concat);
        System.out.println(result4);

        String result5 = letters.stream().reduce("", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase());
        System.out.println(result5);

        List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
        int result6 = users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        System.out.println(result6);

        String result7 = letters.parallelStream().reduce("", String::concat);
        System.out.println(result7);

        int result8 = users.parallelStream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        System.out.println(result8);
    }
}