package com.bottomlord.week_134;

/**
 * @author chen yue
 * @date 2022-02-05 14:06:32
 */
public class LeetCode_1219_1_黄金矿工 {
    private final int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    public int getMaximumGold(int[][] grid) {
        int max = 0, row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                max = Math.max(max, backTrack(grid, row, col, i, j, 0, new boolean[row][col]));
            }
        }

        return max;
    }

    private int backTrack(int[][] grid, int row, int col, int x, int y, int pre, boolean[][] memo) {
        if (x < 0 || x >= row || y < 0 || y >= col || memo[x][y] || grid[x][y] == 0) {
            return pre;
        }

        memo[x][y] = true;
        pre += grid[x][y];
        int curMax = pre;
        for (int[] direction : directions) {
            int newX = x + direction[0], newY = y + direction[1];
            curMax = Math.max(curMax, backTrack(grid, row, col, newX, newY, pre, memo));
        }

        return curMax;
    }
}
