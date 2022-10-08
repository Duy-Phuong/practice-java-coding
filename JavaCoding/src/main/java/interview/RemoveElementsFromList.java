package interview;

// Java Program to remove nulls
// from a List using iterator and Predicate
import java.util.function.Predicate;
import java.util.*;

class GFG {

    // Generic function to remove Null Using Iterator
    public static <T> List<T>
    removeNullUsingIterator(List<T> l, Predicate<T> p)
    {

        // Create an iterator from the l
        Iterator<T> itr = l.iterator();

        // Find and remove all null
        while (itr.hasNext()) {

            // Fetching the next element
            T t = itr.next();

            // System.out.println(t);
            // Checking for Predicate condition
            if (!p.test(t)) {
                System.out.println("remove " + t);
                // If the condition matches,
                // remove that element
                itr.remove();
            }
        }

        // Return the null
        return l;
    }

    public static void main(String[] args)
    {

        // Create the l with null values
        List<String> l = new ArrayList<>(
                Arrays.asList("Geeks",
                        null,
                        "forGeeks",
                        null,
                        "A computer portal"));

        // Print the list
        System.out.println("List with null values: " + l);

        // Creating a Predicate condition checking for null
        Predicate<String> isNull = item -> Objects.nonNull(item);

        // Removing nulls using iterator and Predicate
        l = removeNullUsingIterator(l, isNull);

        // Print the list
        System.out.println("List with null values removed: " + l);
    }
}
