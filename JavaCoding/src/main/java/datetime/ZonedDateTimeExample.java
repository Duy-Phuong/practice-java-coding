package datetime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ZonedDateTimeExample {

    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    public static void main(String[] args) {

        String dateInString = "21-1-2015 10:15:55 AM";
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

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));

        System.out.println("Compare ZonedDateTimeExample:");
        System.out.println(asiaZonedDateTime.isAfter(nyDateTime));
        System.out.println(asiaZonedDateTime.isEqual(nyDateTime));
        System.out.println(asiaZonedDateTime.isBefore(nyDateTime));

        asiaZonedDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
        System.out.println(asiaZonedDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        System.out.println(asiaZonedDateTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        List<String> DAYS_IN_SHORT = Arrays.asList(DayOfWeek.values()).stream()
                .map(d -> d.getDisplayName(TextStyle.SHORT, Locale.ENGLISH))
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(DAYS_IN_SHORT);

        System.out.println("\nConvert LocalDateTime to ZonedDateTime using offset");
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime offsetPlus8 = ldt.atOffset(ZoneOffset.of("+08:00")).toZonedDateTime();
        ZonedDateTime offSetNow = now.atOffset(ZoneOffset.of("+08:00")).toZonedDateTime();
        System.out.println(offsetPlus8);
        System.out.println(offSetNow.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));

        ZonedDateTime current =  ZonedDateTime.now(ZoneId.systemDefault());

        ZonedDateTime current2 =  ZonedDateTime.now(ZoneId.systemDefault());


        System.out.println("------- Convert ZonedDateTime to string -------");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
        String formattedString = zonedDateTimeNow.format(formatter);
        System.out.println(formattedString);
        System.out.println(zonedDateTimeNow.format(formatter1));
        System.out.println(zonedDateTimeNow.format(formatter2));

        System.out.println("---- Convert string to ZonedDateTime ----");
        ZonedDateTime dt2 = ZonedDateTime.parse("2017-May-02 23:35:05",
                DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss").withZone(ZoneId.of("UTC")));
        System.out.println(dt2);

        System.out.println("\nCompare local time");
        LocalTime lt1 = LocalTime.parse("11:37:12");
        LocalTime lt2 = LocalTime.parse("23:15:30");
        System.out.println("The first LocalTime object is: " + lt1);
        System.out.println("The second LocalTime object is: " + lt2);
        int val = lt1.compareTo(lt2);
        if(val > 0)
            System.out.println("\nThe first LocalTime object is greater than the second LocalTimeobject");
        else if(val < 0)
            System.out.println("\nThe first LocalTime object is lesser than the second LocalTimeobject");
        else
            System.out.println("\nThe LocalTime objects are equal");

        LocalTime lt3 = LocalTime.parse("11:37");
        LocalTime lt4 = LocalTime.parse("23:15");
        System.out.println("The first LocalTime object is: " + lt3);
        System.out.println("The second LocalTime object is: " + lt4);
        int val1 = lt3.compareTo(lt4);
        if(val1 > 0)
            System.out.println("\nThe first LocalTime object is greater than the second LocalTimeobject");
        else if(val1 < 0)
            System.out.println("\nThe first LocalTime object is lesser than the second LocalTimeobject");
        else
            System.out.println("\nThe LocalTime objects are equal");

    }

}
