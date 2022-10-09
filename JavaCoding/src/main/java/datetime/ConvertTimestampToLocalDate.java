package datetime;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

public class ConvertTimestampToLocalDate {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        long currentTimeMillis = 400896000;
        String str = "400896000000";
        System.out.println(getDateTimeFromTimestamp(Long.parseLong(str)));
        System.out.println(getDateFromTimestamp(Long.parseLong(str)));


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Long.parseLong(str));
        System.out.println(sf.format(date));
        Optional<Integer> optional = Optional.empty();
        if (optional.isPresent()) {
            System.out.println("have value");
        } else {
            System.out.println("nooooooo");
        }
    }

    public static LocalDateTime getDateTimeFromTimestamp(long timestamp) {
        if (timestamp == 0)
            return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone
                .getDefault().toZoneId());
    }

    public static LocalDate getDateFromTimestamp(long timestamp) {
        LocalDateTime date = getDateTimeFromTimestamp(timestamp);
        return date == null ? null : date.toLocalDate();
    }
}