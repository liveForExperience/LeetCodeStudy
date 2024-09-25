package com.bottomlord.week_123;

/**
 * @author chen yue
 * @date 2021-11-18 08:50:56
 */
public class LeetCode_1854_1_人口最多的年份 {
    public int maximumPopulation(int[][] logs) {
        int[] bucket = new int[100];
        for (int[] log : logs) {
            int begin = log[0] - 1950, end = log[1] - 1950;
            for (int i = begin; i < end; i++) {
                bucket[i]++;
            }
        }

        int max = 0, year = 0;
        for (int i = 0; i < bucket.length; i++) {
            int num = bucket[i];
            if (num > max) {
                year = i;
                max = num;
            }
        }

        return year + 1950;
    }
}
