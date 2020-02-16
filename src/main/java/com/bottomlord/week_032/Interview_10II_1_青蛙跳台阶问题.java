package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/16 11:28
 */
public class Interview_10II_1_青蛙跳台阶问题 {
    public int numWays(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }
}
