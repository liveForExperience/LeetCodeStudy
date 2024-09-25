package com.bottomlord.week_154;

/**
 * @author chen yue
 * @date 2022-06-25 23:12:50
 */
public class LeetCode_offerII091_1_粉刷房子 {
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            int[] cost = costs[i];
            dp[i + 1][0] = Math.min(dp[i][1], dp[i][2]) + cost[0];
            dp[i + 1][1] = Math.min(dp[i][0], dp[i][2]) + cost[1];
            dp[i + 1][2] = Math.min(dp[i][0], dp[i][1]) + cost[2];
        }

        return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
    }
}
