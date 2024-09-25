package com.bottomlord.week_268;

/**
 * @author chen yue
 * @date 2024-08-29 10:06:12
 */
public class LeetCode_3142_1_判断矩阵是否满足条件 {
    public boolean satisfiesConditions(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i != r - 1 && grid[i][j] != grid[i + 1][j] ||
                    j != c - 1 && grid[i][j] == grid[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
