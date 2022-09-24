package files.zip;

import java.io.File;
import java.util.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
public class ZPFSRename {
    public static void main(String [] args) throws Exception {

        /* Define ZIP File System Properies in HashMap */
        Map<String, String> zip_properties = new HashMap<>();
        /* We want to read an existing ZIP File, so we set this to False */
        zip_properties.put("create", "false");

        File a = new File("E:/apps/docs/925e28ef-188f-4913-86d8-103b7fd2c885/4e5c6bee-0379-44b1-af5b-4c1d125a9784/test.zip");
        /* Specify the path to the ZIP File that you want to read as a File System */
        URI zip_disk = a.toURI();
//        URI zip_disk = URI.create("jar:file:/my_zip_file.zip");
//        URI zip_disk = URI.create("jar:file:/my_zip_file.zip");

        /* Create ZIP file System */
        try (FileSystem zipfs = FileSystems.newFileSystem(zip_disk, zip_properties)) {
            /* Access file that needs to be renamed */
            Path pathInZipfile = zipfs.getPath("dest.sql");
            /* Specify new file name */
            Path renamedZipEntry = zipfs.getPath("dest_4.sql");
            System.out.println("About to rename an entry from ZIP File" + pathInZipfile.toUri() );
            /* Execute rename */
            Files.move(pathInZipfile,renamedZipEntry,StandardCopyOption.ATOMIC_MOVE);
            System.out.println("File successfully renamed");
        }
    }
}