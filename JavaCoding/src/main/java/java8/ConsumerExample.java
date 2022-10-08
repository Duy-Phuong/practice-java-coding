package java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ConsumerExample {
//    the Consumer accepts a generified argument and returns nothing
    public static void main(String[] args) {
        Consumer<String> c = s -> System.out.println(s);
        c.accept("I consume data but don't return anything");

        List<String> names = Arrays.asList("John", "Freddy", "Samuel");
        names.forEach(name -> System.out.println("Hello, " + name));

        Map<String, Integer> ages = new HashMap<>();
        ages.put("John", 25);
        ages.put("Freddy", 24);
        ages.put("Samuel", 30);

        ages.forEach((name, age) -> System.out.println(name + " is " + age + " years old"));
    }
}
