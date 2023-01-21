package com.bottomlord.week_184;

/**
 * @author chen yue
 * @date 2023-01-21 12:21:46
 */
public class LeetCode_1824_1_最少侧跳次数 {
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        int[][] dp = new int[n][5];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 2 && i == 0) {
                    continue;
                }

                dp[i][j] = Integer.MAX_VALUE - 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= 3; j++) {
                if (obstacles[i] == j) {
                    continue;
                }

                for (int k = 0; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][k] + 1, dp[i][j]);
                }

                if (i != 0 && obstacles[i - 1] != j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
                }
            }

            for (int j = 3; j >= 1; j--) {
                if (obstacles[i] == j) {
                    continue;
                }

                for (int k = 4; k > j; k--) {
                    dp[i][j] = Math.min(dp[i][k] + 1, dp[i][j]);
                }

                if (i != 0 && obstacles[i - 1] != j) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);
                }
            }
        }

        return Math.min(Math.min(dp[n - 1][1], dp[n - 1][2]), dp[n - 1][3]);
    }
}
