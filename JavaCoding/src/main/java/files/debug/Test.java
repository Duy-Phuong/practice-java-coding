package files.debug;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
//        Optional<String> o = Optional.ofNullable(null);
//        System.out.println(o.isEmpty());
//        System.out.println("A".equalsIgnoreCase(o.get()));

        String s = "    hdhd   \r\n";
        System.out.println("=" + StringUtils.capitalize(s.trim()) + "=");
        System.out.println("=" + StringUtils.capitalize("c") + "=");

        ZonedDateTime lt
                = ZonedDateTime.of(
                2024, 4, 1, 3, 59, 59,
                90000, ZoneId.systemDefault());

        Timestamp timestamp2 = Timestamp.from(lt.toInstant());
        // print result
        System.out.println("ZonedDateTime : "
                + timestamp2.getTime());

//        Optional<String> s = Optional.of(null);
//        System.out.println(s.map(String::length).orElse(null));

        Map<String, String> nameRewritingRules = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        nameRewritingRules.put("xtesthappy", "XTESTHAPPYPATH");
        nameRewritingRules.put("Xtesthappy", "XTESTHAPPYPATH");
        System.out.println(nameRewritingRules.get("xtesthappy"));
        System.out.println(nameRewritingRules.get("XtesThappy"));


        String name = null;
        boolean sd = Optional.ofNullable(name)
                .map(s1 -> s1.toLowerCase())
                .filter(p -> !p.isBlank())
                .isPresent();
        System.out.println(sd);
    }
}
