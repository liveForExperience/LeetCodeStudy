package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/15 19:29
 */
public class Interview_10I_2 {
    public int fib(int n) {
        int[] dp = new int[101];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[n] = dp[n - 1] + dp[n - 2];
        }

        return dp[n];
    }
}
