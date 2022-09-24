package json.country;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class CountryCode {
//    https://github.com/lukes/ISO-3166-Countries-with-Regional-Codes/blob/master/all/all.json
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<PhoneCountryCode> phoneCountryCode = Arrays.asList(
                mapper.readValue(new File("E:\\others\\idtype.json"), PhoneCountryCode[].class));

        List<FullPhoneCountryCode> fullPhoneCountryCode = Arrays.asList(mapper.readValue(new File("E:\\others\\newid.json"), FullPhoneCountryCode[].class));

        List<PhoneCountryCodeAlpha> phoneCountryCodeAlpha = new ArrayList<>();

//        Locale[] availableLocales = Locale.getAvailableLocales();
//        HashMap<String, String> map = new HashMap<String, String>();
//        for ( Locale l : availableLocales ) {
//            if (l.getCountry().equals("CS")) {
//                continue;
//            }
//            map.put( l.getCountry(), l.getISO3Country());
//        }

//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "/" + entry.getValue());
//        }

        phoneCountryCode.forEach((n) -> {
            PhoneCountryCodeAlpha phoneCountryCodeAlpha1 = new PhoneCountryCodeAlpha(n.getCountryName(), n.getCountryCode(), n.getIsoCode());
            phoneCountryCodeAlpha1.setAlpha2Code(getKeyFromFullCountryCode(fullPhoneCountryCode, n.getIsoCode()).toLowerCase(
                    Locale.ROOT));
            phoneCountryCodeAlpha.add(phoneCountryCodeAlpha1);
        });

        //Convert object to JSON string
        String jsonInString = mapper.writeValueAsString(phoneCountryCodeAlpha);
        System.out.println(jsonInString);

        PhoneCountryCodeAlpha test =
                phoneCountryCodeAlpha.stream()
                        .filter((data) -> data.getIsoCode().equalsIgnoreCase("CHE"))
                        .findFirst()
                        .orElse(null);

        System.out.println(test.getAlpha2Code());
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getKeyFromFullCountryCode(List<FullPhoneCountryCode> map, String value) {
        AtomicReference<String> result = new AtomicReference<>("");
        map.forEach((n) -> {
            if (n.getAlpha3().equalsIgnoreCase(value)) {
                result.set(n.getAlpha2());
            }
        });
        return result.get();
    }
}