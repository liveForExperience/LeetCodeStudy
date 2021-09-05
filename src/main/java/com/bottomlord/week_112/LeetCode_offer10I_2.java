package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-05 23:17:46
 */
public class LeetCode_offer10I_2 {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
