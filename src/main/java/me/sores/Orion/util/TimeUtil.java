package me.sores.Orion.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by sores on 1/6/2022.
 */
public class TimeUtil {

    public static boolean elapsed(long from, long required) {
        return System.currentTimeMillis() - from > required;
    }

    public static long now() {
        return nowMillis() / 1000L;
    }

    public static long nowMillis() {
        return System.currentTimeMillis();
    }

    public static String getFormattedTime(int seconds) {
        return getFormattedTime((long) seconds * 1000);
    }

    public static String getFormattedTime(long millis) {
        boolean ago = false;
        if (millis < 0) {
            ago = true;

            millis = Math.abs(millis);
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder();
        if (days != 0) {
            sb.append(days).append("d");
        }

        if (hours != 0) {
            sb.append(" ").append(hours).append("h");
        }

        if (minutes != 0) {
            sb.append(" ").append(minutes).append("m");
        }

        if (seconds != 0) {
            sb.append(" ").append(seconds).append("s");
        }

        return (sb.toString().trim() + (ago ? " ago" : ""));
    }

}
