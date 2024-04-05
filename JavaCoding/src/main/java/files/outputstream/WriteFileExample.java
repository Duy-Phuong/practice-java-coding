package files.outputstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFileExample {
    public static void main(String[] args) {

        File file = new File("newfile.txt");
        String content = "This is the text content";

        try (FileOutputStream fop = new FileOutputStream(file)) {

            // if file doesn't exists, then create it
            // no need to create it manually
//            if (!file.exists()) {
//                file.createNewFile();
//            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
