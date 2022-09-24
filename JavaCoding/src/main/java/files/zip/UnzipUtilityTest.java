package files.zip;

public class UnzipUtilityTest {
    public static void main(String[] args) {
        String zipFilePath = "E:/apps/docs/925e28ef-188f-4913-86d8-103b7fd2c885/4e5c6bee-0379-44b1-af5b-4c1d125a9784/test.zip";
        String destDirectory = "E:/apps/docs/925e28ef-188f-4913-86d8-103b7fd2c885/4e5c6bee-0379-44b1-af5b-4c1d125a9784";
        UnzipUtility unzipper = new UnzipUtility();
        try {
            unzipper.unzip(zipFilePath, destDirectory);
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
    }
}