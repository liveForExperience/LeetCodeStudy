package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/25 11:16
 */
public class LeetCode_766_1_托普利茨矩阵 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }

        return true;
    }
}
