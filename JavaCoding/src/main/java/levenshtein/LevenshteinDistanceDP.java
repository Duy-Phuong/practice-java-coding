package levenshtein;

// Java implementation of Levenshtein distance calculation
// Using Dynamic Programming (Optimised solution)

import java.util.Arrays;
class LevenshteinDistanceDP {

  static int compute_Levenshtein_distanceDP(String str1, String str2)
  {

    // A 2-D matrix to store previously calculated
    // answers of subproblems in order
    // to obtain the final

    int[][] dp = new int[str1.length() + 1][str2.length() + 1];

    for (int i = 0; i <= str1.length(); i++)
    {
//      System.out.println();
      for (int j = 0; j <= str2.length(); j++) {

        // If str1 is empty, all characters of
        // str2 are inserted into str1, which is of
        // the only possible method of conversion
        // with minimum operations.
        if (i == 0) {
          dp[i][j] = j;
        }

        // If str2 is empty, all characters of str1
        // are removed, which is the only possible
        // method of conversion with minimum
        // operations.
        else if (j == 0) {
          dp[i][j] = i;
        }

        else {
          // find the minimum among three
          // operations below

          dp[i][j] = minm_edits(dp[i - 1][j - 1]
                  + NumOfReplacement(str1.charAt(i - 1), str2.charAt(j - 1)), // replace
              dp[i - 1][j] + 1, // delete
              dp[i][j - 1] + 1); // insert
        }
//        System.out.print(dp[i][j] + "  ") ;
      }
    }
    System.out.println();
    System.out.println("---- length ------");
    System.out.println(str1.length());
    System.out.println(str2.length());
    System.out.println("----------");
    return dp[str1.length()][str2.length()];
  }

  // check for distinct characters
  // in str1 and str2

  static int NumOfReplacement(char c1, char c2)
  {
    return c1 == c2 ? 0 : 1;
  }

  // receives the count of different
  // operations performed and returns the
  // minimum value among them.

  static int minm_edits(int... nums)
  {

    return Arrays.stream(nums).min().orElse(
        Integer.MAX_VALUE);
  }

  static double calculatePercentage(String s1, String s2) {
    int levDis = compute_Levenshtein_distanceDP(s1, s2);
    int bigger = Math.max(s1.length(), s2.length());
    System.out.println("bigger - levDis: " + (bigger - levDis) );
    System.out.println("Max: " + bigger);
    return ((double)(bigger - levDis) * 100) / bigger;
  }

  // Driver Code
  public static void main(String args[])
  {

//    String s1 = "glomax";
//    String s2 = "folmax";
//    String s1 = "sunday";
//    String s2 = "saturday";

//    String s1 = "giky";
//    String s2 = "geeky";

//    String s1 = "Lawndaring";
//    String s2 = "money";

//    String s1 = "Michael";
//    String s2 = "Michaes";

//    String s1 = "Orell";
//    String s2 = "Ohasdbja";

//    String s1 = "sitting";
//    String s2 = "kitten";

//    String s1 = "sdfsdfsdfsd";
//    String s2 = "kitten";

//    String s1 = "Michael Messerli somewhere long how are you";
//    String s2 = "Michaes Messr11 this does not look good i am fine";

    String s1 = "sjghjhds asjkdajksdhjkasd ajskdajsd amsbdjabsd asjdasdasdasd sdfdsfnbmwemnmrbmwnerbmnwebrmnwebrmnwer";
    String s2 = ",dnas,dn,asndkjashndjabs nd asdasdasdx ahsdhgsadjhgasdjhgasdhjgadsjhgdhsgadgasjagsdjgasdjasd";

    System.out.println(s1);
    System.out.println(s2);
    long startTime = System.nanoTime();
    System.out.println(calculatePercentage(s1, s2));
    System.out.println("Levenshtein point: ");
    System.out.println(compute_Levenshtein_distanceDP(s1, s2));
    long elapsedTime = System.nanoTime() - startTime;

    System.out.println("Total execution time to create 1000K objects in Java in millis: "
        + elapsedTime/1000000);
  }
}
