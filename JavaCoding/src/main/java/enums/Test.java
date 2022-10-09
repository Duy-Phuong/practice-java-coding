package enums;

// Java program to demonstrate that enums can have constructor
// and concrete methods.

import java.util.Arrays;

// An enum (Note enum keyword inplace of class keyword)
enum Color
{
  RED("1"), GREEN("2"), BLUE("3");

  private String value;

  // enum constructor called separately for each
  // constant
  private Color()
  {
    System.out.println("Constructor called for : " +
        this.toString());
  }

  private Color(String value) {
    this.value = value;
  }

  public void colorInfo()
  {
    System.out.println("Universal Color");
  }

//  public static Color fromValue(String text) {
//    for (Color b : Color.values()) {
//      if (String.valueOf(b.value).equals(text)) {
//        return b;
//      }
//    }
//    return null;
//  }

  public static Color fromValue(String text) {
    return Arrays.stream(Color.values())
        .filter(object -> String.valueOf(object.value).equals(text))
        .findFirst().orElse(null);
  }
}

public class Test
{
  // Driver method
  public static void main(String[] args)
  {
    Color c1 = Color.RED;
    System.out.println(c1.ordinal());
    c1.colorInfo();
    System.out.println(Color.fromValue("2"));
  }
}