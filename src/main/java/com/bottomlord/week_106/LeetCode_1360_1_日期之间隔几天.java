package com.bottomlord.week_106;

/**
 * @author ChenYue
 * @date 2021/7/22 8:37
 */
public class LeetCode_1360_1_日期之间隔几天 {
    public int daysBetweenDates(String date1, String date2) {
        int[] yearSum = new int[2101];
        int[] monthSum = new int[13];
        int[] month = new int[]{0, 31,28,31,30,31,30,31,31,30,31,30,31};

        for (int i = 1971; i <= 2100; i++) {
            yearSum[i] = yearSum[i - 1] + (isLeapYear(i) ? 366 : 365);
        }

        for (int i = 1; i <= 12; i++) {
            monthSum[i] = monthSum[i - 1] + month[i];
        }

        String[] dates1 = date1.split("-");
        int year1 = Integer.parseInt(dates1[0]);
        int month1 = Integer.parseInt(dates1[1]);
        int day1 = Integer.parseInt(dates1[2]);

        String[] dates2 = date2.split("-");
        int year2 = Integer.parseInt(dates2[0]);
        int month2 = Integer.parseInt(dates2[1]);
        int day2 = Integer.parseInt(dates2[2]);

        int sum1 = yearSum[year1 - 1] + monthSum[month1 - 1] + day1;
        if (isLeapYear(year1) && month1 > 2) {
            sum1++;
        }

        int sum2 = yearSum[year2 - 1] + monthSum[month2 - 1] + day2;
        if (isLeapYear(year2) && month2 > 2) {
            sum2++;
        }

        return Math.abs(sum1 - sum2);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0);
    }
}
