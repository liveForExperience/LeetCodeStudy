package com.bottomlord.week_035;

/**
 * @author ThinkPad
 * @date 2020/3/2 8:52
 */
public class Interview_47_3 {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;

        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }

        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }

        return grid[row - 1][col - 1];
    }
}