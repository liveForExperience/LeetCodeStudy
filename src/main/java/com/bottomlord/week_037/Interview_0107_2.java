package com.bottomlord.week_037;

/**
 * @author ThinkPad
 * @date 2020/3/16 8:50
 */
public class Interview_0107_2 {
    public void rotate(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i; j++) {
                matrix[i][j] += matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col / 2; j++) {
                matrix[i][j] += matrix[i][col - 1 - j];
                matrix[i][col - 1 - j] = matrix[i][j] - matrix[i][col - 1 - j];
                matrix[i][j] = matrix[i][j] - matrix[i][col - 1 - j];
            }
        }
    }
}