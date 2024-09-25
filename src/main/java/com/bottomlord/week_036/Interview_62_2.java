package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/10 13:07
 */
public class Interview_62_2 {
    public int lastRemaining(int n, int m) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + m) % i;
        }
        return dp[n];
    }
}