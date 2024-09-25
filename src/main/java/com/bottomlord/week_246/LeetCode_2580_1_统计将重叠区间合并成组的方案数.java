package com.bottomlord.week_246;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chen yue
 * @date 2024-03-27 20:43:48
 */
public class LeetCode_2580_1_统计将重叠区间合并成组的方案数 {
    public int countWays(int[][] ranges) {
        int ans = 1, mod = 1000000007, n = ranges.length;
        Arrays.sort(ranges, Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < ranges.length;) {
            int end = ranges[i][1];
            while (i < n && ranges[i][1] <= end) {
                end = Math.max(end, ranges[i][1]);
                i++;
            }

            ans = ans * 2 % mod;
        }

        return ans;
    }
}
