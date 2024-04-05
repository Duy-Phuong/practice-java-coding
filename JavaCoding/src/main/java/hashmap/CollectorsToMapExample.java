package hashmap;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.geeksforgeeks.org/collectors-tomap-method-in-java-with-examples/
public class CollectorsToMapExample {
    public static void main(String[] args) {
        // Create a String to be converted
        Stream<String[]>
                Ss1 = Stream
                .of(new String[][] { { "GFG", "GeeksForGeeks" },
                        { "g", "geeks" },
                        { "GFG", "Geeks" } });

        // Get Map from String
        // using toMap() method
        LinkedHashMap<String, String>
                map2 = Ss1
                .collect(Collectors
                        .toMap(
                                p -> p[0], p -> p[1], (s, a) -> s + ", " + a, LinkedHashMap::new));

        // Print the Map
        System.out.println("Map:" + map2);
        // Map:{GFG=GeeksForGeeks, Geeks, g=geeks}
    }
}
