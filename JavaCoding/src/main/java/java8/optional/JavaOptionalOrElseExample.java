package java8.optional;

import java.util.Optional;
import java.util.Random;

public class JavaOptionalOrElseExample {
    public static String getRandomName() {
        String[] names = new String[] { "test", "test2", "test3", "test4" };
        System.out.println("getRandomName() method - start");

        Random random = new Random();
        int index = random.nextInt(4);

        System.out.println("getRandomName() method - end");
        return names[index];
    }

    public static void main(String[] args) {
        // With it, we can easily infer that the parameter of orElse() is evaluated, even when having a non-empty Optional.
        String name = Optional.of("baeldung")
                .orElse(getRandomName());

        System.out.println(name);

        String name2 = Optional.of("baeldung")
                .orElseGet(() -> getRandomName());

        System.out.println(name2);
    }
}
