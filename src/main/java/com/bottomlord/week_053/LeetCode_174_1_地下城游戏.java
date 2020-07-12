package com.bottomlord.week_053;

/**
 * @author ChenYue
 * @date 2020/7/12 17:28
 */
public class LeetCode_174_1_地下城游戏 {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        if (row == 0) {
            return 0;
        }

        int col = dungeon[0].length;
        if (col == 0) {
            return 0;
        }

        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                dp[i][col] = Integer.MAX_VALUE;
                dp[row][j] = Integer.MAX_VALUE;
            }
        }

        dp[row - 1][col] = 1;
        dp[row][col - 1] = 1;

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }
}
