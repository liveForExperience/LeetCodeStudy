package com.bottomlord.week_099;

public class LeetCode_1185_1_一周中的第几天 {
    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = new String[]{"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        int[] monthDays = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int count = 0;
        for (int i = 1971; i < year; i++) {
            count += 365;
            if (isLeapYear(i)) {
                count++;
            }
        }

        for (int i = 1; i < month; i++) {
            count += monthDays[i];
        }

        count = isLeapYear(year) ? count + day + 1 : count + day;
        return days[count % 7 - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 100 != 0 && year % 4 == 0) || year % 400 == 0;
    }
}
