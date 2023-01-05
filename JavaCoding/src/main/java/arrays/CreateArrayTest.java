package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CreateArrayTest {
    public static void main(String[] args) {
        List<Integer> numbers
                = Arrays.asList(11, 22, 33, 44,
                55, 66, 77, 88,
                99, 100);

        // External iterator, for Each loop
        for (Integer n : numbers) {
            System.out.print(n + " ");
        }

        // Creating an arrayList object
        // Declaring object of String type
        ArrayList<String> gfgNames = new ArrayList<>();

        // Custom input elements to above object
        gfgNames.add("Dean");
        gfgNames.add("castee");
        gfgNames.add("robert");

        // Creating object of Stream where Stream is created
        // using arrayList and object as data source
        Stream<String> streamOfNames = gfgNames.stream();

        // Print and display element
        System.out.print(streamOfNames);


        // Data Source
        Integer[] numbers1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

        // Stream
        Stream<Integer> streamOfNumbers
                = Arrays.stream(numbers1);

        // filter all the even numbers
        Stream<Integer> evenNumbersStream
                = streamOfNumbers.filter(
                number -> number % 2 == 0);


    }
}
