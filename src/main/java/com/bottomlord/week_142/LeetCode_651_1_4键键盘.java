package com.bottomlord.week_142;

/**
 * @author chen yue
 * @date 2022-03-29 22:01:15
 */
public class LeetCode_651_1_4键键盘 {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;

            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[j] * (i - j - 1), dp[i]);
            }
        }

        return dp[n];
    }
}
