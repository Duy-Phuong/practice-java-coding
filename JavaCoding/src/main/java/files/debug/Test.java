package files.debug;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
//        Optional<String> o = Optional.ofNullable(null);
//        System.out.println(o.isEmpty());
//        System.out.println("A".equalsIgnoreCase(o.get()));

        ZonedDateTime lt
                = ZonedDateTime.of(
                2024, 4, 1, 3, 59, 59,
                90000, ZoneId.systemDefault());

        Timestamp timestamp2 = Timestamp.from(lt.toInstant());
        // print result
        System.out.println("ZonedDateTime : "
                + timestamp2.getTime());     }
}
