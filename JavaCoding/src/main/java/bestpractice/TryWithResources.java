package bestpractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryWithResources {
    public static void main(String[] args) {
        // old approach
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("test.txt"));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        // new approach
        try (Scanner scanner2 = new Scanner(new File("test.txt"))) {
            while (scanner2.hasNext()) {
                System.out.println(scanner2.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }
}
