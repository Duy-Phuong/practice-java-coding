package interview;

// Java program to remove leading/preceding zeros
// from a given string
import java.util.Arrays;
import java.util.List;

/* Name of the class to remove leading/preceding zeros */
class RemoveZero
{
    public static String removeZero(String str)
    {
        // Count leading zeros
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0')
            i++;

        // Convert str into StringBuffer as Strings
        // are immutable.
        StringBuffer sb = new StringBuffer(str);

        // The StringBuffer replace function removes
        // i characters from given index (0 here)
        sb.replace(0, i, "");

        return sb.toString(); // return in String
    }

    // Driver code
    public static void main (String[] args)
    {
        String str = "00000123569";
        str = removeZero(str);
        System.out.println(str);
    }
}
