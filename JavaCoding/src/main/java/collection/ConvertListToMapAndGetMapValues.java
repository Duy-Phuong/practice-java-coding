package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConvertListToMapAndGetMapValues {
    public static void main(String[] args) {
        Map<UUID, String> map = new HashMap<>();
        map.put(UUID.randomUUID(), "A");
        map.put(UUID.randomUUID(), "B");
        map.put(UUID.randomUUID(), "A");
        map.put(UUID.randomUUID(), "C");
        map.put(UUID.randomUUID(), "A");

        // Loop
        for (Map.Entry<UUID, String> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

        // Get values from map
        Set<String> values = new HashSet<>(map.values());
        System.out.println(values);

        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
        bookList.add(new Book("The Two Towers", 1954, "0345339711"));
        bookList.add(new Book("The Return of the King", 1955, "0618129111"));

//        It will throw a duplicated exception
//        Map<Integer, Book> integerBookMap = listToMapWithDupKeyError(bookList);
//        System.out.println(integerBookMap);

        // Convert list to Map
        Map<Integer, Book> integerBookMap = listToMapWithDupKey(bookList);
        System.out.println(integerBookMap);
    }

    public static Map<String, String> listToMap(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getNumber, Book::getName));
    }

    public static Map<Integer, Book> listToMapWithDupKeyError(List<Book> books) {
        return books.stream().collect(
                Collectors.toMap(Book::getYear, Function.identity()));
    }

    public static Map<Integer, Book> listToMapWithDupKey(List<Book> books) {
        return books.stream().collect(Collectors.toMap(Book::getYear, Function.identity(),
                (existing, replacement) -> existing));
    }

    static class Book {
        public String name;
        public int year;
        public String number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Book(String name, int year, String number) {
            this.name = name;
            this.year = year;
            this.number = number;
        }
    }
}
