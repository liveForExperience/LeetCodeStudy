package com.bottomlord.week_064;

/**
 * @author ChenYue
 * @date 2020/9/23 11:01
 */
public class LeetCode_276_1_栅栏涂色 {
    public int numWays(int n, int k) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = k;
            } else if (i == 2) {
                dp[i] = dp[i - 1] * k;
            } else {
                dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
            }

        }

        return dp[n];
    }
}
