package com.bottomlord.week_077;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ChenYue
 * @date 2020/12/31 8:51
 */
public class LeetCode_435_1_无重叠区域 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int len = intervals.length;

        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[j][1] <= intervals[i][0]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        return max;
    }
}
