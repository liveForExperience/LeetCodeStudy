package com.bottomlord.week_029;

/**
 * @author ThinkPad
 * @date 2020/1/26 12:07
 */
public class LeetCode_650_2 {
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + i / j;
                }
            }
        }

        return dp[n];
    }
}