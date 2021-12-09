package com.bottomlord.week_126;

/**
 * @author chen yue
 * @date 2021-12-08 16:03:58
 */
public class LeetCode_1034_1_边界着色 {
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int r = grid.length, c = grid[0].length;
        boolean[][] memo = new boolean[r][c];

        backTrack(grid, row, col, r, c, grid[row][col], color, memo);
        return grid;
    }

    private boolean backTrack(int[][] grid, int row, int col, int r, int c, int target, int color, boolean[][] memo) {
        if (row < 0 || row >= r || col < 0 || col >= c) {
            return false;
        }

        if (grid[row][col] != target) {
            return false;
        }

        if (memo[row][col]) {
            return true;
        }

        memo[row][col] = true;

        for (int[] dir : dirs) {
            boolean result = backTrack(grid, row + dir[0], col + dir[1],  r, c, target, color, memo);
            if (!result) {
                grid[row][col] = color;
            }
        }

        return true;
    }
}
