package com.bottomlord.week_141;

/**
 * @author chen yue
 * @date 2022-03-24 09:30:05
 */
public class LeetCode_634_1_寻找数组的错位排列 {
    public int findDerangement(int n) {
        if (n == 1) {
            return 0;
        }

        int mod = 1000000007;

        long[] dp = new long[n + 1];
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
            dp[i] %= mod;
        }

        return (int)dp[n];
    }
}
