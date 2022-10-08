package stream;

import java.util.Arrays;

public class ConvertArrayToStream {
    public static void main(String[] args) {
        String[] colors = {"Red", "Blue", "Green"};
        Arrays.stream(colors).map(String::toUpperCase).toArray();

        System.out.println(colors);


    }
}
