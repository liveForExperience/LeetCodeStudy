package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/29 18:11
 */
public class LeetCode_1162_2 {
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = grid[i][j] == 0 ? Integer.MAX_VALUE : 0;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }

                if (i - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1 < 0 ? Integer.MAX_VALUE : dp[i - 1][j] + 1);
                }

                if (j - 1 >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1 < 0 ? Integer.MAX_VALUE : dp[i][j - 1] + 1);
                }
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (grid[i][j] != 0) {
                    continue;
                }

                if (i + 1 < row) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1 < 0 ? Integer.MAX_VALUE : dp[i + 1][j] + 1);
                }

                if (j + 1 < col) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1 < 0 ? Integer.MAX_VALUE : dp[i][j + 1] + 1);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.max(dp[i][j], ans);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}