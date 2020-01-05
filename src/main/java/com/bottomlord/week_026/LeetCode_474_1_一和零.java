package com.bottomlord.week_026;

/**
 * @author ThinkPad
 * @date 2020/1/4 19:45
 */
public class LeetCode_474_1_一和零 {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int[] count = count(str);

            for (int zeros = m; zeros >= count[0]; zeros--) {
                for (int ones = n; ones >= count[1]; ones--) {
                    dp[zeros][ones] = Math.max(1 + dp[zeros - count[0]][ones - count[1]], dp[zeros][ones]);
                }
            }
        }

        return dp[m][n];
    }

    private int[] count(String str) {
        int[] count = new int[2];
        for (char c : str.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }
}
