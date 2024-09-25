package com.bottomlord.contest_20220626;

/**
 * @author chen yue
 * @date 2022-06-25 23:22:54
 */
public class Contest_1_1_判断矩阵是一个X矩阵 {
    public boolean checkXMatrix(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j || i + j == r - 1) {
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
