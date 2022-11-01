package scanner;

import java.util.Scanner;

//https://www.w3schools.com/java/java_user_input.asp
public class ScannerUserInput {
    public static void main(String[] args) {
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter username");
//
//        String userName = myObj.nextLine();  // Read user input
//        System.out.println("Username is: " + userName);  // Output user input

        Scanner scan = new Scanner(System.in);

        // Note: Keep the order of the output in order
        // Note: If you use the nextLine() method immediately following the nextInt() method,
        // recall that nextInt() reads integer tokens; because of this,
        // the last newline character for that line of integer input is still
        // queued in the input buffer and the next nextLine() will be reading the remainder
        // of the integer line (which is empty).
        int i = scan.nextInt();

        double d = scan.nextDouble();

        // Clear buffer
        scan.next();
        // Or you can use the nextLine() method
//        scan.nextLine();
        String s = scan.nextLine();

        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);


        scan.close();

    }
}
