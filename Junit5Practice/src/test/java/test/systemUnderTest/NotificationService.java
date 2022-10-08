package test.systemUnderTest;


public class NotificationService {

    private static final NotificationService instance = new NotificationService();

    public static NotificationService getInstance() {
        return instance;
    }

    public static boolean sendNotification(String message) {
        System.out.println("Sending notification : " + message);
        return true;
    }
}
