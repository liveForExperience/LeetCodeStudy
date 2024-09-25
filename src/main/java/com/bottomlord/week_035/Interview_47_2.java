package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/2 8:39
 */
public class Interview_47_2 {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.max(i + 1 < row ? dp[i + 1][j] : 0, j + 1 < col ? dp[i][j + 1] : 0);
            }
        }

        return dp[0][0];
    }
}