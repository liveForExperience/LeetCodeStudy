package com.bottomlord.week_060;

/**
 * @author ChenYue
 * @date 2020/8/25 8:37
 */
public class LeetCode_240_1_探索二维矩阵II {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) {
            return false;
        }

        int col = matrix[0].length;
        if (col == 0) {
            return false;
        }

        for (int[] rows : matrix) {
            if (rows[col - 1] == target) {
                return true;
            }

            if (rows[col - 1] < target) {
                continue;
            }

            for (int c = 0; c < col - 1; c++) {
                if (rows[c] == target) {
                    return true;
                }
            }
        }

        return false;
    }
}
