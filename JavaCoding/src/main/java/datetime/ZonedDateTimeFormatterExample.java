package datetime;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeFormatterExample {

    public static void main(String[] args) {
        // Create a ZonedDateTime in a specific time zone (e.g., America/New_York)
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Zurich"));

        // Create a DateTimeFormatter with the desired format
//        Formatted ZonedDateTime: 2023-08-08 16:43:22 CEST (GMT+2)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z (O)");

        // Format the ZonedDateTime using the DateTimeFormatter
        String formattedZonedDateTime = zonedDateTime.format(formatter);

        System.out.println("Formatted ZonedDateTime: " + formattedZonedDateTime);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("0");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("OOOO");

//        Formatted ZonedDateTime: GMT+2
        System.out.println("Formatted ZonedDateTime: " + zonedDateTime.format(formatter1));
//        Formatted ZonedDateTime: GMT+02:00
        System.out.println("Formatted ZonedDateTime: " + zonedDateTime.format(formatter2));

    }
}
