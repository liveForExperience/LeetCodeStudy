package com.bottomlord.week_128;

/**
 * @author chen yue
 * @date 2021-12-21 08:48:17
 */
public class LeetCode_1154_1_一年中的第几天 {
    public int dayOfYear(String date) {
        int[] monthDays = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

        String[] factors = date.split("-");
        int year = Integer.parseInt(factors[0]),
            month = Integer.parseInt(factors[1]),
            day = Integer.parseInt(factors[2]);

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
