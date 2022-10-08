package files;

import java.io.*;

public class ReadUTF8Data {
    public static void main(String[] args) {
        try {
            //            File fileDir = new File("c:\\temp\\test.txt");
            File fileDir = new File("D:\\Source\\test\\src\\file\\test.txt");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fileDir), "UTF8"));

            String str;

            while ((str = in.readLine()) != null) {
                if (str.indexOf(":") > 0) {
                    if (str.startsWith("Ex")) {
                        System.out.println(str);
                    } else {
                        //                        System.out.println(str.substring(0, str.indexOf(":")));
                        System.out.println(str.substring(str.indexOf(": ") + 2));
                    }

                } else if (!str.trim().isEmpty()){
                    System.out.println(str);
                }
            }

            in.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}