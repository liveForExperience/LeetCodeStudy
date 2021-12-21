package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-21 09:03:18
 */
public class LeetCode_1154_2 {
    public int dayOfYear(String date) {
        int[] monthDays = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

        int year = 1000 * (date.charAt(0) - '0') + 100 * (date.charAt(1) - '0') + 10 * (date.charAt(2) - '0') + (date.charAt(3) - '0'),
            month = 10 * (date.charAt(5) - '0') + (date.charAt(6) - '0'),
            day = 10 * (date.charAt(8) - '0') + (date.charAt(9) - '0');

        if (month <= 2) {
            return monthDays[month - 1] + day;
        }

        return monthDays[month - 1] + day + (isLeapYear(year) ? 1 : 0);
    }

    private boolean isLeapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }

        return year % 4 == 0;
    }
}
