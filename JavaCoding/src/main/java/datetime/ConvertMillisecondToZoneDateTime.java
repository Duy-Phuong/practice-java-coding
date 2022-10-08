package datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ConvertMillisecondToZoneDateTime {
    public static void main (String[] args) {

        String epochValue = "1573543685000";
        epochValue = "0";
        //330 minutes/ 5:30 hours (client/UI is on GMT IST timezone)
        System.out.println("ZoneId : " + epochMilliSToZDT(epochValue));
        System.out.println("ZoneOffset : " + epochMilliSToZDT(epochValue, 330));
        //        ZoneId : 1970-01-01T07:00+07:00[Asia/Bangkok]
        //        ZoneOffset : 1970-01-01T05:30+05:30
    }

    // Case i) ZoneId
    private static ZonedDateTime epochMilliSToZDT(String  epochMilliSeconds) {
        Long lEpochMilliSeconds = Long.parseLong(epochMilliSeconds);
        return ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(lEpochMilliSeconds), ZoneId.systemDefault());
    }


    //Case ii) ZoneOffset - Adjusting timezone between client and server
    private static ZonedDateTime epochMilliSToZDT(String  epochMilliSeconds, int utcOffsetVal) {
        Long lEpochMilliSeconds = Long.parseLong(epochMilliSeconds);
        ZoneOffset zoneOffSet= ZoneOffset.ofTotalSeconds(utcOffsetVal * 60);
        return ZonedDateTime.ofInstant(
                Instant.ofEpochMilli(lEpochMilliSeconds), zoneOffSet);
    }

}