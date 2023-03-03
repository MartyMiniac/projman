package projman.helpers;

import java.util.Calendar;

public class HashGen {
    public static String generate() {
        Calendar c = Calendar.getInstance();
        long hash = (c.get(Calendar.YEAR)-2000)*365*24*60*60;
        hash += c.get(Calendar.MONTH)*30*24*60*60;
        hash += c.get(Calendar.DATE)*24*60*60;
        hash += c.get(Calendar.HOUR_OF_DAY)*60*60;
        hash += c.get(Calendar.MINUTE)*60;
        hash += c.get(Calendar.SECOND);

        return hash+"";
    }
}
