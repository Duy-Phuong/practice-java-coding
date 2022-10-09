package mrz;

import java.time.LocalDate;

public class MRZConverter {

    public static void main(String[] args) {
//        System.out.println("\n======== Input MRZ to convert to normal name ===========");
//        String s = "I<UTOSTEVENSON<<PETER<<<<<<<<<<<<<<<\nD231458907UTO3407127M9507122<<<<<<<2";
//        System.out.println(s.length());
//        final MrzRecord record = MrzParser.parse(s);
//        System.out.println("Name: " + record.givenNames + " " + record.surname);

//        String s1 = "DR., PETER's, STEVENSON, UKHSFJKSD-HF123123";
//        showResult(s1);
//        showResult2("PETER's,", "STEVENSON, UKHSFJKSD-HF123123");
//        String s2 = "Isabelle Rüedi Ñ Ø Æ Ä";
//        showResult(s2);
//        showResult2("Isabelle Rüedi ", " Ñ Ø Æ Ä");
//        String s3 = "BENNELONG WOOLOOMOOLOO WARRANDYTE WARNAMBOOL";
//        showResult(s3);

        String s11 = "III PETER's, STEVIIENSON, III-HF123123II II";
        showResult(s11);
        LocalDate date = LocalDate.now().minusDays(3);
        System.out.println(date);

    }

    private static void showResult(String s3) {
        System.out.println("\n======== Input name to convert to MRZ ===========");
        String initialName = UbiIdMrzParser.toMrz(s3, 38);
        System.out.println(initialName);
        System.out.println(initialName.replace("<", " ").trim());
    }

    private static void showResult2(String surname, String givenNames){
        System.out.println("\n======== 2222222222222222 ===========");
        String initialName = UbiIdMrzParser.nameToMrz(surname, givenNames, 35);
        System.out.println(initialName);
        System.out.println(initialName.replace("<", " ").trim().replaceAll("( )+", " "));
    }
}
