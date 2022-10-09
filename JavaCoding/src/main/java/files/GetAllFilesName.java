package files;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetAllFilesName {

  public static void main(String[] args) {
//    File folder = new File("your/path");
    getAllFileName();
  }

  private static void getAllFileName() {
    Path path = Paths.get("/app/uploads/925e28ef-188f-4913-86d8-103b7fd2c885/0376de93-a412-4fcb-a8f6-dc74ad72f55a");
    File folder = path.toFile();
    File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        String filename = listOfFiles[i].getName();
        System.out.println("File " + filename.substring(0, filename.lastIndexOf(".")));
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
  }
}
