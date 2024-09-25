package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-16 14:19:16
 */
public class LeetCode_offerII88_1_爬楼梯的最少成本 {
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length - 1; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        return Math.min(dp[len - 1], dp[len - 2]);
    }
}
