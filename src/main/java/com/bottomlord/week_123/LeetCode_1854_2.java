package com.bottomlord.week_123;

/**
 * @author chen yue
 * @date 2021-11-18 09:00:21
 */
public class LeetCode_1854_2 {
    public int maximumPopulation(int[][] logs) {
        int[] bucket = new int[101];
        for (int[] log : logs) {
            bucket[log[0] - 1950]++;
            bucket[log[1] - 1950]--;
        }

        int max = 0, year = 0, cur = 0;
        for (int i = 0; i < bucket.length; i++) {
            cur += bucket[i];
            if (cur > max) {
                year = i + 1950;
                max = cur;
            }
        }

        return year;
    }
}