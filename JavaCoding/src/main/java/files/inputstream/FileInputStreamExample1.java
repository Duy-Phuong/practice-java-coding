package files.inputstream;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample1 {
    public static void main(String args[])
            throws IOException
    {

        // Attaching the file to FileInputStream: /Users/phuong/Documents/developer/development/private-source/obsidian/practice-java-coding/JavaCoding/input.txt
        FileInputStream fin
                = new FileInputStream("input.txt");

        // Illustrating getChannel() method
        System.out.println(fin.getChannel());

        // Illustrating getFD() method
        System.out.println(fin.getFD());

        // Illustrating available method
        System.out.println("Number of remaining bytes:"
                + fin.available());

        // Illustrating skip() method
        fin.skip(4);

        // Display message for better readability
        System.out.println("FileContents :");

        // Reading characters from FileInputStream
        // and write them
        int ch;

        // Holds true till there is data inside file
        while ((ch = fin.read()) != -1)
            System.out.print((char)ch);

        // Close the file connections
        // using close() method
        fin.close();

//        sun.nio.ch.FileChannelImpl@3b6eb2ec
//        java.io.FileDescriptor@1e643faf
//        Number of remaining bytes:12
//        FileContents :
//        o world!
    }
}
