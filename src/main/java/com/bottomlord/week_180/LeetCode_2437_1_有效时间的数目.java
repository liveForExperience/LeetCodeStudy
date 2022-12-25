package com.bottomlord.week_180;

/**
 * @author chen yue
 * @date 2022-12-25 19:26:38
 */
public class LeetCode_2437_1_有效时间的数目 {
    public int countTime(String time) {
        String[] factors = time.split(":");
        String hour = factors[0], minute = factors[1];
        return generateHour(hour) * generateMinute(minute);
    }

    private int generateHour(String hour) {
        if (!hour.contains("?")) {
            return 1;
        }

        if (hour.charAt(0) == '?' && hour.charAt(1) == '?') {
            return 24;
        }

        if (hour.charAt(0) == '?') {
            return hour.charAt(1) >= '4' ? 2 : 3;
        }

        return hour.charAt(0) < '2' ? 10 : 4;
    }

    private int generateMinute(String minute) {
        if (!minute.contains("?")) {
            return 1;
        }

        if (minute.charAt(0) == '?' && minute.charAt(1) == '?') {
            return 60;
        }

        if (minute.charAt(0) == '?') {
            return 6;
        }

        return 10;
    }
}
