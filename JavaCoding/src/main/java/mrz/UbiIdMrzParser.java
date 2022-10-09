/**
 * Java parser for the MRZ records, as specified by the ICAO organization.
 * Copyright (C) 2011 Innovatrics s.r.o.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package mrz;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UbiIdMrzParser {


    private static final Map<String, String> EXPAND_CHARACTERS = new HashMap<String, String>();
    private static final List<String> SPECIAL_CHARACTERS = new ArrayList<String>() {
        {
            add("’");
            add("'");
            add(",");
            add("Dr.");
            add("Sir");
            add("Jr.");
            add("Sr.");
        }
    };

    static {
        EXPAND_CHARACTERS.put("\u00C4", "AE"); // Ä
        EXPAND_CHARACTERS.put("\u00E4", "AE"); // ä
        EXPAND_CHARACTERS.put("\u00C5", "AA"); // Å
        EXPAND_CHARACTERS.put("\u00E5", "AA"); // å
        EXPAND_CHARACTERS.put("\u00C6", "AE"); // Æ
        EXPAND_CHARACTERS.put("\u00E6", "AE"); // æ
        EXPAND_CHARACTERS.put("\u0132", "IJ"); // Ĳ
        EXPAND_CHARACTERS.put("\u0133", "IJ"); // ĳ
        EXPAND_CHARACTERS.put("\u00D6", "OE"); // Ö
        EXPAND_CHARACTERS.put("\u00F6", "OE"); // ö
        EXPAND_CHARACTERS.put("\u00D8", "OE"); // Ø
        EXPAND_CHARACTERS.put("\u00F8", "OE"); // ø
        EXPAND_CHARACTERS.put("\u00DC", "UE"); // Ü
        EXPAND_CHARACTERS.put("\u00FC", "UE"); // ü
        EXPAND_CHARACTERS.put("\u00DF", "SS"); // ß
    }

    /**
     * Converts given string to a MRZ string: removes all accents, converts the string to upper-case and replaces all spaces and invalid characters with '&lt;'.
     * <p/>
     * Several characters are expanded:
     * <table border="1">
     * <tr><th>Character</th><th>Expand to</th></tr>
     * <tr><td>Ä</td><td>AE</td></tr>
     * <tr><td>Å</td><td>AA</td></tr>
     * <tr><td>Æ</td><td>AE</td></tr>
     * <tr><td>Ĳ</td><td>IJ</td></tr>
     * <tr><td>IJ</td><td>IJ</td></tr>
     * <tr><td>Ö</td><td>OE</td></tr>
     * <tr><td>Ø</td><td>OE</td></tr>
     * <tr><td>Ü</td><td>UE</td></tr>
     * <tr><td>ß</td><td>SS</td></tr>
     * </table>
     * <p/>
     * Examples:<ul>
     * <li><code>toMrz("Sedím na konári", 20)</code> yields <code>"SEDIM&lt;NA&lt;KONARI&lt;&lt;&lt;&lt;&lt;"</code></li>
     * <li><code>toMrz("Pat, Mat", 8)</code> yields <code>"PAT&lt;&lt;MAT"</code></li>
     * <li><code>toMrz("foo/bar baz", 4)</code> yields <code>"FOO&lt;"</code></li>
     * <li><code>toMrz("*$()&/\", 8)</code> yields <code>"&lt;&lt;&lt;&lt;&lt;&lt;&lt;&lt;"</code></li>
     * </ul>
     * @param string the string to convert. Passing null is the same as passing in an empty string.
     * @param length required length of the string. If given string is longer, it is truncated. If given string is shorter than given length, '&lt;' characters are appended at the end. If -1, the string is neither truncated nor enlarged.
     * @return MRZ-valid string.
     */
    public static String toMrz(String string, int length) {
        if (string == null) {
            string = "";
        }
        for (final Map.Entry<String, String> e : EXPAND_CHARACTERS.entrySet()) {
            string = string.replace(e.getKey(), e.getValue());
        }
        string = removeSpecialCharacters(string);
        string = deaccent(string).toUpperCase();
        if (length >= 0 && string.length() > length) {
            string = string.substring(0, length);
        }
        final StringBuilder sb = new StringBuilder(string);
        for (int i = 0; i < sb.length(); i++) {
            if (!isValid(sb.charAt(i))) {
                sb.setCharAt(i, FILLER);
            }
        }
        while (sb.length() < length) {
            sb.append(FILLER);
        }
        return sb.toString();
    }

    private static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    private static String removeSpecialCharacters(String s) {
        s = s.toUpperCase();
        if (s.startsWith("III ")) {
            s = s.substring(3);
        }
        if (s.startsWith("II ")) {
            s = s.substring(2);
        }
        if (s.endsWith(" III")) {
            s = s.substring(0, s.length() - 4);
        }
        if (s.endsWith(" II")) {
            s = s.substring(0, s.length() - 3);
        }
        for (int i = 0; i < SPECIAL_CHARACTERS.size(); i++) {
            s = s.replace(SPECIAL_CHARACTERS.get(i).toUpperCase(), "");
        }
        return s;
    }

    /**
     * Converts a surname and given names to a MRZ string, shortening them as per Doc 9303 Part 3 Vol 1 Section 6.7 of the MRZ specification when necessary.
     * @param surname the surname, not blank.
     * @param givenNames given names, not blank.
     * @param length required length of the string. If given string is longer, it is shortened. If given string is shorter than given length, '&lt;' characters are appended at the end.
     * @return name, properly converted to MRZ format of SURNAME&lt;&lt;GIVENNAMES&lt;..., with the exact length of given length.
     */
    public static String nameToMrz(String surname, String givenNames, int length) {
        if (isBlank(surname)) {
            throw new IllegalArgumentException("Parameter surname: invalid value " + surname + ": blank");
        }
        if (isBlank(givenNames)) {
            throw new IllegalArgumentException("Parameter givenNames: invalid value " + givenNames + ": blank");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Parameter length: invalid value " + length + ": not positive");
        }
        surname = surname.replace(", ", " ");
        givenNames = givenNames.replace(", ", " ");
        final String[] surnames = surname.trim().split("[ \n\t\f\r]+");
        final String[] given = givenNames.trim().split("[ \n\t\f\r]+");
        for (int i = 0; i < surnames.length; i++) {
            surnames[i] = toMrz(surnames[i], -1);
        }
        for (int i = 0; i < given.length; i++) {
            given[i] = toMrz(given[i], -1);
        }
        // truncate
        int nameSize = getNameSize(surnames, given);
        String[] currentlyTruncating = given;
        int currentlyTruncatingIndex = given.length - 1;
        while (nameSize > length) {
            final String ct = currentlyTruncating[currentlyTruncatingIndex];
            final int ctsize = ct.length();
            if (nameSize - ctsize + 1 <= length) {
                currentlyTruncating[currentlyTruncatingIndex] = ct.substring(0, ctsize - (nameSize - length));
            } else {
                currentlyTruncating[currentlyTruncatingIndex] = ct.substring(0, 1);
                currentlyTruncatingIndex--;
                if (currentlyTruncatingIndex < 0) {
                    if (currentlyTruncating == surnames) {
                        throw new IllegalArgumentException("Cannot truncate name " + surname + " " + givenNames + ": length too small: " + length + "; truncated to " + toName(surnames, given));
                    }
                    currentlyTruncating = surnames;
                    currentlyTruncatingIndex = currentlyTruncating.length - 1;
                }
            }
            nameSize = getNameSize(surnames, given);
        }
        return toMrz(toName(surnames, given), length);
    }
    /**
     * The filler character, '&lt;'.
     */
    public static final char FILLER = '<';

    private static String toName(String[] surnames, String[] given) {
        final StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String s : surnames) {
            if (first) {
                first = false;
            } else {
                sb.append(FILLER);
            }
            sb.append(s);
        }
        sb.append(FILLER);
        for (String s : given) {
            sb.append(FILLER);
            sb.append(s);
        }
        return sb.toString();
    }

    private static int getNameSize(final String[] surnames, final String[] given) {
        int result = 0;
        for (String s : surnames) {
            result += s.length() + 1;
        }
        for (String s : given) {
            result += s.length() + 1;
        }
        return result;
    }

    private static String deaccent(String str) {
        String n = Normalizer.normalize(str, Normalizer.Form.NFD);
        return n.replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    /**
     * Checks if given character is valid in MRZ.
     * @param c the character.
     * @return true if the character is valid, false otherwise.
     */
    private static boolean isValid(char c) {
        return ((c == FILLER) || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'));
    }

    private static int getCharacterValue(char c) {
        if (c == FILLER) {
            return 0;
        }
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 10;
        }
        throw new RuntimeException("Invalid character in MRZ record: " + c);
    }
}
