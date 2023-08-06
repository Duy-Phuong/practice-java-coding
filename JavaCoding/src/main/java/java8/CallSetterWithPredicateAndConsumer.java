package java8;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public class CallSetterWithPredicateAndConsumer {
    public static <T> void doIfTrue(T value, Predicate<T> validator, Consumer<T> consumer) {
        if (validator.test(value)) {
            consumer.accept(value);
        }
    }

    public static <T> void doIfPresent(T value, Consumer<T> consumer) {
        doIfTrue(value, java.util.Objects::nonNull, consumer);
    }

    public static void doIfNotBlank(String value, Consumer<String> consumer) {
        doIfTrue(value, StringUtils::isNotBlank, consumer);
    }

    public static void doIfNotBlank1(String value, Consumer<String> consumer) {
        if (StringUtils.isNotBlank(value)) {
            consumer.accept(value);
        }
    }

    public static <T> void setValueIfNotNull(T value, Predicate<T> predicate, Consumer<T> consumer) {
        if (predicate.test(value)) {
            consumer.accept(value);
        }
    }

    public static <T> void setValueIfNotNull1(T value, Predicate<T> predicate, Consumer<T> consumer) {
        doIfTrue(value, predicate, consumer);
    }

    public static <T> void setValueFieldIfNotNull(String value, Predicate<String> predicate, Consumer<String> consumer) {
        if (predicate.test(value)) {
            consumer.accept(value);
        }
    }

    public static <T> void setValueFieldIfNotNull1(T value, Predicate<T> predicate, Consumer<T> consumer) {
        if (predicate.test(value)) {
            consumer.accept(value);
        }
    }

    public static void main(String[] args) {
        Person person = new Person("John Doe");
        Person person2 = new Person("John2");

        CallSetterWithPredicateAndConsumer.setValueIfNotNull(person, p -> p != null, p -> p.setName("Jane Smith"));

        System.out.println(person.getName());

        CallSetterWithPredicateAndConsumer.setValueIfNotNull1(person, p -> p != null, p -> p.setName("John done"));

        System.out.println(person.getName());

        CallSetterWithPredicateAndConsumer.setValueFieldIfNotNull1("New name for field", p -> p != null, person::setName);

        System.out.println(person.getName());

    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}