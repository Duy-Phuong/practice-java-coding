package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NullSafeCollectionMain {

  public static void main(String[] args) {
    System.out.println("The stream after applying "
        + "the function is : ");

    // Creating a list of Integers
//    List<Integer> list = Arrays.asList(3, 6, 9, 12, 15);
    List<Integer> list = null;

    // Using Stream map(Function mapper) and
    // displaying the corresponding new stream
    NullSafeCollectionStreams.collectionAsStream(list).map(number -> number * 3).forEach(System.out::println);

    List<String> list1 = Arrays.asList("Abc", "ABC");
    List<String> list2 = Arrays.asList("bc", "ABC");
    Set<String> a = new HashSet<>(list1.stream().map(String::toLowerCase).collect(Collectors.toList()));
    a.addAll(list2.stream().map(String::toLowerCase).collect(Collectors.toList()));

    List<String> arr = new ArrayList<>(a);
    System.out.println(arr);
    System.out.println(arr.get(1));


    List<String> memberNames = new ArrayList<>();
    memberNames.add("Amitabh");
    memberNames.add("Shekhar");
    memberNames.add("Aman");
    memberNames.add("Rahul");
    memberNames.add("Shahrukh");
    memberNames.add("Salman");
    memberNames.add("Yana");
    memberNames.add("Lokesh");

    memberNames.stream().sorted()
        .map(String::toUpperCase)
        .forEach(System.out::println);

  }
}
