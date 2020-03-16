package com.bottomlord.week_037;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/16 9:19
 */
public class Interview_0108_2 {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int row = matrix.length, col = matrix[0].length;
        boolean[] rows = new boolean[row], cols = new boolean[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            if (rows[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }

        for (int i = 0; i < col; i++) {
            if (cols[i]) {
                for (int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}