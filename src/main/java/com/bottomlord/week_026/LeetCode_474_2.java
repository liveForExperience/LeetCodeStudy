package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2020/1/5 11:49
 */
public class LeetCode_474_2 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int count0 = 0, count1 = 0;
            for (char c : str.toCharArray()) {
                 if (c == '0') {
                     count0++;
                 } else {
                     count1++;
                 }
            }

            for (int zeros = m; zeros >= count0; zeros--) {
                for (int ones = n; ones >= count1; ones--) {
                    dp[zeros][ones] = Math.max(dp[zeros][ones], 1 + dp[zeros - count0][ones - count1]);
                }
            }
        }

        return dp[m][n];
    }
}