package com.bottomlord.week_012;

public class LeetCode_64_1_最小路径和 {
    private int min = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
        recurse(grid, 0, 0, grid.length - 1, grid[0].length - 1, 0);
        return min;
    }

    private void recurse(int[][] grid, int row, int col, int finalRow, int finalCol, int sum) {
        if (sum > min || row > finalRow || col > finalCol) {
            return;
        }

        sum += grid[row][col];

        if (row == finalRow && col == finalCol) {
            min = Math.min(min, sum);
            return;
        }

        recurse(grid, row + 1, col, finalRow, finalCol, sum);
        recurse(grid, row, col + 1, finalRow, finalCol, sum);
    }
}
