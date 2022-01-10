package com.bottomlord.week_131;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-01-10 20:48:16
 */
public class LeetCode_2037_1_使每位学生都有座位的最少移动次数 {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }
        return sum;
    }
}
