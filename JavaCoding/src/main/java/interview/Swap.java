package interview;

/**
 * Java program to swap two integers without using temp variable * * @author java67
 */
public class Swap {
    public static void main(String args[]) {
        int a = 10;
        int b = 20; // one way using arithmetic operator e.g. + or - // won't work if sum overflows
        System.out.println("One way to swap two numbers without temp variable");
        System.out.printf("Before swap 'a': %d, 'b': %d %n", a, b);
        a = a + b;
        b = a - b; // actually (a + b) - (b), so now b is equal to a a = a - b; // (a + b) -(a), now a is equal to b
        System.out.printf("After swapping, 'a': %d, 'b': %d %n", a, b); // another exampl
        a = Integer.MIN_VALUE;
        b = Integer.MAX_VALUE;
        System.out.printf("Before swap 'a': %d, 'b': %d %n", a, b);
        a = (a + b) - (b = a);
        System.out.printf("After swapping, 'a': %d, 'b': %d %n", a, b);
        // Another way to swap integers without using temp variable is
        // by using XOR bitwise operator
        // Known as XOR trick
        System.out.println("Swap two integers without third variable using XOR bitwise Operator");
        int x = 30;
        int y = 60;
        System.out.printf("Before swap 'x': %d, 'y': %d %n", x, y);
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.printf("After swapping, 'x': %d, 'y': %d %n", x, y);
    }
}
// https://www.java67.com/2015/08/how-to-swap-two-integers-without-using.html?fbclid=IwAR2hc6j8vbtajmBqHiVwTTaBfkPgfYziTByj55_m_NDEXl08fayghoc23NQ#ixzz6NGzFObFu
//    Output One
//    way to
//    swap two
//    numbers without
//    temp variable
//    Before swap 'a':10,'b':20
//    After swapping,'a':20,'b':10
//    Before swap'a':-2147483648,'b':2147483647
//    After swapping,'a':2147483647,'b':-2147483648
//    Swap two integers without third variable using XOR bitwise Operator
//    Before swap'x':30,'y':60
//    After swapping,'x':60,'y':30

