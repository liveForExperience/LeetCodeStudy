package com.bottomlord.week_152;

/**
 * @author chen yue
 * @date 2022-06-11 23:46:45
 */
public class LeetCode_926_1_将字符串翻转到单调递增 {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        int[][] dp = new int[n + 1][2];
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = dp[i][0] + (cs[i] == '0' ? 0 : 1);
            dp[i + 1][1] = Math.min(dp[i][1], dp[i][0]) + (cs[i] == '1' ? 0 : 1);
        }

        return Math.min(dp[n][0], dp[n][1]);
    }
}
