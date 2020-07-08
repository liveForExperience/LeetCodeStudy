package com.bottomlord.week_053;

/**
 * @author ChenYue
 * @date 2020/7/8 8:45
 */
public class LeetCode_123_1_买卖股票的最佳时机III {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < n; i++) {
            for (int j = 2; j >= 1; j--) {
                if (i == 0) {
                    dp[i][j][1] = -prices[i];
                    dp[i][j][0] = 0;
                    continue;
                }

                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }

        return dp[n - 1][2][0];
    }
}
