package com.bottomlord.week_012;

public class LeetCode_64_3 {
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[] dp = new int[colLen];
        for (int i = rowLen - 1; i >= 0; i--) {
            for (int j = colLen - 1; j >= 0; j--) {
                if (i == rowLen - 1 && j == colLen - 1) {
                    dp[j] = grid[i][j];
                } else if (i == rowLen - 1 && j != colLen - 1) {
                    dp[j] = grid[i][j] + dp[j + 1];
                } else if (i != rowLen - 1 && j == colLen - 1) {
                    dp[j] = grid[i][j] + dp[j];
                } else {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                }
            }
        }

        return dp[0];
    }
}
