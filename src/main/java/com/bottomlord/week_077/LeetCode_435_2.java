package com.bottomlord.week_077;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ChenYue
 * @date 2021/1/2 12:57
 */
public class LeetCode_435_2 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));

        int right = intervals[0][1], count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                count++;
                right = intervals[i][1];
            }
        }

        return intervals.length - count;
    }
}