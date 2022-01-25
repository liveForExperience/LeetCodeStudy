package com.bottomlord.week_133;

/**
 * @author chen yue
 * @date 2022-01-25 21:40:19
 */
public class LeetCode_2133_1_查是否每一行每一列都包含全部整数 {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        int sum = (1 + n) * n / 2;

        for (int i = 0; i < n; i++) {
            boolean[] row = new boolean[n], col = new boolean[n];
            for (int j = 0; j < n; j++) {
                row[matrix[i][j] - 1] = true;
                col[matrix[j][i] - 1] = true;
            }

            if (!full(row) || !full(col)) {
                return false;
            }
        }

        return true;
    }

    private boolean full(boolean[] arr) {
        for (boolean b : arr) {
            if (!b) {
                return false;
            }
        }

        return true;
    }
}
