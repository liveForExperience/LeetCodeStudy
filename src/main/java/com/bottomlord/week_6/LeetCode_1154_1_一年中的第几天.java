package com.bottomlord.week_6;

public class LeetCode_1154_1_一年中的第几天 {
    public int dayOfYear(String date) {
        int[] dayOfMonth = new int[] {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        String[] factors = date.split("-");
        int year = Integer.parseInt(factors[0]);
        int month = Integer.parseInt(factors[1]);
        int day = Integer.parseInt(factors[2]);

        int days = 0;
        if (month > 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
            days += 1;
        }

        for (int i = 1; i < month; i++) {
            days += dayOfMonth[i];
        }

        days += day;

        return days;
    }
}
