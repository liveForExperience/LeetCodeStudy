package com.bottomlord.contest_20220619;

/**
 * @author chen yue
 * @date 2022-06-19 10:17:35
 */
public class Contest_4_1_卖木头块 {
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];

        for (int[] price : prices) {
            int x = price[0], y = price[1];
            dp[x][y] = price[2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k <= i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }

                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }

        return dp[m][n];
    }
}
