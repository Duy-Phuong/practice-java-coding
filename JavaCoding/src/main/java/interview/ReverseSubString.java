package interview;

// Java implementation of the approach
import java.util.*;

class ReverseSubString
{

    // Function to return the string after
    // reversing characters in the range [L, R]
    static String reverse(char[] str, int len,
            int l, int r)
    {

        // Invalid range
        if (l < 0 || r >= len || l > r)
            return "Invalid range!";

        // While there are characters to swap
        while (l < r)
        {

            // Swap(str[l], str[r])
            char c = str[l];
            str[l] = str[r];
            str[r] = c;

            l++;
            r--;
        }
        String string = new String(str);
        return string;
    }

    // Driver code
    public static void main (String[] args)
    {
        String str = "geeksforgeeks";
        int len = str.length();
        int l = 5, r = 7;

        System.out.println(reverse(str.toCharArray(),
                len, l, r));
    }
}

// This code is contributed by Ashutosh450
