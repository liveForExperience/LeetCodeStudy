package com.bottomlord.week_135;

/**
 * @author chen yue
 * @date 2022-02-09 09:12:16
 */
public class LeetCode_offerII003_2 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        return dp;
    }
}
