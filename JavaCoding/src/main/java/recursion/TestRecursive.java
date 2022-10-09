package recursion;

public class TestRecursive {

  public static long factorial(int n) {
    if (n == 1) return 1;
    return n * factorial(n-1);
  }

  public static void factorialTest(int n) {
    System.out.println(n);
    if (n == 1) return;
    factorialTest(n-1);
  }

  public static void factorialTest2(int n) {
    System.out.println(n);
    if (n < 3 && n != 1) {
      factorialTest2(n + 1);
      return;
    }
    System.out.println("end");
  }

  public static void factorialTest3(int n) {
    try {
      System.out.println(n);
      int a = Integer.parseInt(null);
    } catch(NumberFormatException ex) {
      if (n < 2) {
        factorialTest3(++n); // not n++
//        return;
      } else {
        System.err.println("Invalid string in argument: " + n);
        //request for well-formatted string
      }
    }
  }


  public static void main(String[] args) {
//    factorialTest(2);
    factorialTest3(0);
//    String s = "12";
//    String s3 = null;
//    String s2 = s + s3;
//    System.out.println(s2);
//    System.out.println(MessageFormat.format("Override existing identity with body '{'\"email\": \"{0}\"'}'", ""));

  }

}
