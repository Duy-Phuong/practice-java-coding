package files.inputstream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedInputStreamExample {
    // https://www.programiz.com/java-programming/bufferedinputstream
    public static void main(String[] args) {
        try {

            // Suppose, the input.txt file contains the following text
            // This is a line of text inside the file.
            FileInputStream file = new FileInputStream("input.txt");

            // Creates a BufferedInputStream
            BufferedInputStream buffer = new BufferedInputStream(file);

            // Returns the available number of bytes
            System.out.println("Available bytes at the beginning: " + buffer.available());

            // Reads 3 bytes from the file
            System.out.println((char)buffer.read());
            System.out.println((char)buffer.read());
            System.out.println((char)buffer.read());

            // Returns the available number of bytes
            System.out.println("Available bytes at the end: " + buffer.available());

            buffer.close();

//            Only read 3 bytes
//            Available bytes at the beginning: 12
//            H
//            e
//            l
//            Available bytes at the end: 9
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
