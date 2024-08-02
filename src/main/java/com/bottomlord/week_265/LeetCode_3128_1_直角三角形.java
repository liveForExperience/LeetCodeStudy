package com.bottomlord.week_265;

/**
 * @author chen yue
 * @date 2024-08-02 23:24:03
 */
public class LeetCode_3128_1_直角三角形 {
    public long numberOfRightTriangles(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rows = new int[col], cols = new int[row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rows[j] += grid[i][j];
                cols[i] += grid[i][j];
            }
        }

        long cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cnt += (long) (rows[j] - 1) * (cols[i] - 1) * grid[i][j];
            }
        }

        return cnt;
    }
}
