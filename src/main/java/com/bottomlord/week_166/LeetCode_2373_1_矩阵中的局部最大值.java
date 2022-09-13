package com.bottomlord.week_166;

/**
 * @author chen yue
 * @date 2022-09-14 06:06:15
 */
public class LeetCode_2373_1_矩阵中的局部最大值 {
    public int[][] largestLocal(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][] maxLocal = new int[r - 2][c - 2];
        for (int i = 0; i < r - 2; i++) {
            for (int j = 0; j < c - 2; j++) {
                maxLocal[i][j] = findMax(grid, i + 1, j + 1);
            }
        }
        return maxLocal;
    }

    private int findMax(int[][] grid, int r, int c) {
        int max = Integer.MIN_VALUE;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }

        return max;
    }
}
