package datetime;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class ZoneDateTimePractice {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    public static void main(String[] args) {
        String dateInString = "21-1-2015 01:15:55 AM";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);

        ZoneId americaZoneId = ZoneId.of("America/New_York");
        System.out.println("TimeZone : " + americaZoneId);

        ZonedDateTime americaDateTime = asiaZonedDateTime.withZoneSameInstant(americaZoneId);
        System.out.println("Date America) : " + americaDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));
        System.out.println("Date (America) : " + format.format(americaDateTime));

        System.out.println("Compare ZonedDateTimeExample:");
        System.out.println(asiaZonedDateTime.isAfter(nyDateTime));
        System.out.println(asiaZonedDateTime.isEqual(nyDateTime));
        System.out.println(asiaZonedDateTime.isBefore(nyDateTime));
        System.out.println(americaDateTime.isEqual(nyDateTime));

        asiaZonedDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
        System.out.println(asiaZonedDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        System.out.println(asiaZonedDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println(nyDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println(americaDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));

        System.out.println("------ OFFSET -------");
        String dateInString2 = "21-1-2015 01:15:55 AM";
        String dateInString3 = "21-1-2015 00:15:55 AM";
        String dateInString4 = "20-1-2015 12:15:55 PM";
        LocalDateTime ldt2 = LocalDateTime.parse(dateInString2, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime ldt3 = LocalDateTime.parse(dateInString3, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDateTime ldt4 = LocalDateTime.parse(dateInString4, DateTimeFormatter.ofPattern(DATE_FORMAT));
        ZonedDateTime zdt6 = ldt2.atOffset(ZoneOffset.of("+08:00")).toZonedDateTime();
        ZonedDateTime zdt7 = ldt3.atOffset(ZoneOffset.of("+07:00")).toZonedDateTime();
        ZonedDateTime zdt8 = ldt4.atOffset(ZoneOffset.of("-05:00")).toZonedDateTime();
        System.out.println(zdt6.toString());
        System.out.println(zdt7.toString());
        System.out.println(zdt8.toString());
        System.out.println(zdt6.isBefore(zdt7));
        System.out.println(zdt6.isEqual(zdt7));
        System.out.println(zdt6.isAfter(zdt7));
        System.out.println(zdt8.isEqual(zdt7));

        System.out.println(zdt6.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println(zdt7.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println(zdt8.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));

        System.out.println(" ---------- Init ZonedDateTime with LocalDateTime using UTC offset ------------");
        LocalDateTime lct = LocalDateTime.now();
        ZonedDateTime zlct1 = lct.atOffset(ZoneOffset.of("+08:00")).toZonedDateTime();
        ZonedDateTime zlct2 = lct.atOffset(ZoneOffset.of("+07:00")).toZonedDateTime();
        ZonedDateTime zlct3 = lct.atOffset(ZoneOffset.of("-05:00")).toZonedDateTime();
        System.out.println(zlct1);
        System.out.println(zlct2);
        System.out.println(zlct3);
        System.out.println(zlct1.isEqual(zlct2));
        System.out.println(zlct3.isEqual(zlct2));

        System.out.println("---------------- Show current state ----------------");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println("ZoneId.systemDefault(): " + ZoneId.systemDefault());

        // convert LocalDateTime to ZonedDateTime, with default system zone id
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());

        // convert LocalDateTime to ZonedDateTime, with specified zoneId
        ZonedDateTime europeDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Kaliningrad"));
        System.out.println(europeDateTime);

        // convert LocalDateTime to ZonedDateTime, with specified off set
//        ZonedDateTime offSetNegative5 = now.atOffset(ZoneOffset.of("-05:00")).toZonedDateTime();
//        System.out.println(offSetNegative5);

//        now = LocalDateTime.now();
        ZonedDateTime offSetNegative6 = now.atOffset(ZoneOffset.of("-06:00")).toZonedDateTime();
        System.out.println(offSetNegative6);

        // Wrong way to change offset
        ZonedDateTime japanDateTime = offSetNegative6.withZoneSameInstant(ZoneOffset.of("-06:00"));
        System.out.println(japanDateTime);

        // display all zone ids
        //ZoneId.getAvailableZoneIds().forEach(System.out::println);

        System.out.println("------------  To change the offset without adjusting LocalDateTime  ----------------");
        OffsetDateTime now1 = OffsetDateTime.now();
        System.out.println(now1);
        // Init ZonedDateTime with time zone offset
        ZonedDateTime time = LocalDateTime.now().atOffset(ZoneOffset.of("+09:00")).toZonedDateTime();
        System.out.println(time);

        OffsetDateTime nowInUTC = now1.withOffsetSameInstant(ZoneOffset.of( "+09:00" ));
        OffsetDateTime nowIn2 = now1.withOffsetSameInstant(ZoneOffset.of( "+05:00" ));
        ZonedDateTime test1 = nowInUTC.toZonedDateTime();
        ZonedDateTime test2 = nowIn2.toZonedDateTime();

        System.out.println(nowInUTC.toZonedDateTime());
        System.out.println(nowIn2.toZonedDateTime());

        System.out.println(nowInUTC.isEqual(nowIn2));
        System.out.println(test1.isEqual(test2));
        System.out.println(time.isEqual(test1));
    }
}
