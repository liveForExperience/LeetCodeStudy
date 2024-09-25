package com.bottomlord.week_037;

/**
 * @author ThinkPad
 * @date 2020/3/16 8:46
 */
public class Interview_0107_1_旋转矩阵 {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int row = matrix.length, col = matrix[0].length;
        int[][] ans = new int[col][row];

        for (int i = 0; i < col; i++) {
            for (int j = row - 1; j >= 0; j--) {
                ans[i][col - j - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < row; i++) {
            System.arraycopy(ans[i], 0, matrix[i], 0, col);
        }
    }
}
