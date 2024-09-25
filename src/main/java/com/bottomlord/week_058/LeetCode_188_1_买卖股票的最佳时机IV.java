package com.bottomlord.week_058;

/**
 * @author ChenYue
 * @date 2020/8/12 17:54
 */
public class LeetCode_188_1_买卖股票的最佳时机IV {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) {
                    dp[i][0][j] = 0;
                    dp[i][1][j] = -prices[i];
                    continue;
                }

                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }

        return dp[n - 1][0][k];
    }
}
