package com.bottomlord.week_051;

/**
 * @author ChenYue
 * @date 2020/6/27 13:02
 */
public class LeetCode_74_1_搜索二维矩阵 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int row = matrix.length, col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            if (i != row - 1) {
                int nextRowStart = matrix[i + 1][0];

                if (nextRowStart == target) {
                    return true;
                }

                if (nextRowStart < target) {
                    continue;
                }
            }

            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }

            return false;
        }

        return false;
    }
}
