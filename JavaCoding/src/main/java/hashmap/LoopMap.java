package hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoopMap {

    public static void main(String[] args) {

        // initial a Map
        Map<String, String> map = new HashMap<>();
        map.put("1", "Jan");
        map.put("2", "Feb");
        map.put("3", "Mar");
        map.put("4", "Apr");
        map.put("5", "May");
        map.put("6", "Jun");

        // Standard classic way, recommend!
        System.out.println("\nExample 1...");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }

        // Java 8, forEach and Lambda. recommend!
        System.out.println("\nExample 2...");
        map.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        // Map -> Set -> Iterator -> Map.Entry -> troublesome, don't use, just for fun
        System.out.println("\nExample 3...");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("Key : " + entry.getKey() + " Value :" + entry.getValue());
        }

        // weired, but works anyway, don't use, just for fun
        System.out.println("\nExample 4...");
        for (Object key : map.keySet()) {
            System.out.println("Key : " + key.toString() + " Value : " + map.get(key));
        }

    }

}