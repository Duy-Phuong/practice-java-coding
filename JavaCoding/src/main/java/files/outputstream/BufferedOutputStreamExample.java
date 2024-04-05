package files.outputstream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class BufferedOutputStreamExample {
    public static void main(String args[])throws Exception
    {
        // https://www.programiz.com/java-programming/bufferedoutputstream
        FileOutputStream fout = new FileOutputStream("output.txt");

        //creating bufferdOutputStream obj
        BufferedOutputStream bout = new BufferedOutputStream(fout);

        //illustrating write() method
        for(int i = 65; i < 75; i++)
        {
            bout.write(i);
        }

        byte b[] = { 75, 76, 77, 78, 79, 80 };
        bout.write(b);

        //illustrating flush() method
        bout.flush();

        //illustrating close() method
        bout.close();
        fout.close();

        // ouput.txt: ABCDEFGHIJKLMNOP
    }
}
