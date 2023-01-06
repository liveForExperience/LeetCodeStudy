package com.bottomlord.week_182;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-01-07 06:49:39
 */
public class LeetCode_2500_2 {
    public int deleteGreatestValue(int[][] grid) {
        for (int[] arr : grid) {
            Arrays.sort(arr);
        }

        int ans = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int max = Integer.MIN_VALUE;

            for (int[] arr : grid) {
                max = Math.max(max, arr[i]);
            }

            ans += max;
        }

        return ans;
    }
}
