package files.pdf;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class BufferedOutputStreamExample {
    public static void main(String args[]) throws IOException {
//        FileOutputStream fout = null;
//        BufferedOutputStream bout = null;
//
//        try {
//            fout = new FileOutputStream("E:\\testout.txt");
//            bout = new BufferedOutputStream(fout);
//            String s = "Welcome to java.";
//            byte b[] = s.getBytes();
//            bout.write(b);
//            bout.flush();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            bout.close();
//            fout.close();
//        }

        String data = "test/4.pdf";
//        data = data.replace("/", "/test_");
        data = data.substring(data.lastIndexOf("/") + 1);

        String transactionNumber = "4";
        String input = "4/4_security2.jpg";
        UUID caseId = UUID.randomUUID();
        String result = "";
        if (input.contains(".pdf")) {
            result = input.replace("/" + transactionNumber, "/intrumReport_" + caseId);
        } else if (input.contains(".xml")) {
            result =  input.replace("/" + transactionNumber, "/idData_" + caseId);
        } else if (input.contains("idbackside")) {
            result = input.replace(transactionNumber + "_idbackside", "back_" + caseId);
        } else if (input.contains("idfrontside")) {
            result = input.replace(transactionNumber + "_idfrontside", "front_" + caseId);
        } else if (input.contains("userface")) {
            result = input.replace(transactionNumber + "_userface", "photo_" + caseId);
        } else if (input.contains("security1")) {
            result = input.replace(transactionNumber + "_security1", "security1_" + caseId);
        } else if (input.contains("liveness_right")) {
            result = input.replace(transactionNumber + "_liveness_right", "liveness_right_" + caseId);
        } else if (input.contains("liveness_left")) {
            result = input.replace(transactionNumber + "_liveness_left", "liveness_left_" + caseId);
        } else {
            result = input.replace(transactionNumber + "_", "");
            result = result.replace(".", "_" + caseId + ".");
        }

        System.out.println(result);

        System.out.println("success!");

//        File a = new File("E:/apps/docs/925e28ef-188f-4913-86d8-103b7fd2c885/4e5c6bee-0379-44b1-af5b-4c1d125a9784/test.zip");
//        System.out.println(a.getAbsolutePath());
//        System.out.println(a.getParent());

    }
}