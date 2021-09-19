package com.bottomlord.week_108;

/**
 * @author chen yue
 * @date 2021-08-08 14:13:52
 */
public class LeetCode_1137_2 {
    public int tribonacci(int n) {
        if (n <= 1) {
            return n;
        }

        if (n == 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
}
