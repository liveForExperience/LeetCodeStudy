package com.bottomlord.week_156;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-07-11 09:11:28
 */
public class LeetCode_741_1_摘樱桃 {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[2 * n - 1][n][n];
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }

        dp[0][0][0] = grid[0][0];

        for (int k = 1; k < 2 * n - 1; k++) {
            for (int x1 = Math.max(k - n + 1, 0); x1 <= Math.min(k, n - 1); x1++) {
                int y1 = k - x1;

                if (grid[x1][y1] == -1) {
                    continue;
                }

                for (int x2 = Math.max(k - n + 1, 0); x2 <= Math.min(k, n - 1); x2++) {
                    int y2 = k - x2;

                    if (grid[x2][y2] == -1) {
                        continue;
                    }

                    int ans = dp[k - 1][x1][x2];
                    if (x1 > 0) {
                        ans = Math.max(dp[k - 1][x1 - 1][x2], ans);
                    }

                    if (x2 > 0) {
                        ans = Math.max(dp[k - 1][x1][x2 - 1], ans);
                    }

                    if (x1 > 0 && x2 > 0) {
                        ans = Math.max(dp[k][x1 - 1][x2 - 1], ans);
                    }

                    ans += grid[x1][y1];
                    if (x1 != x2) {
                        ans += grid[x2][y2];
                    }

                    dp[k][x1][x2] = ans;
                }
            }
        }

        return Math.max(0, dp[2 * n - 2][n - 1][n - 1]);
    }
}
