package com.bottomlord.week_037;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/3/16 9:10
 */
public class Interview_0108_1_零矩阵 {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int row = matrix.length, col = matrix[0].length;
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int i : rows) {
            Arrays.fill(matrix[i], 0);
        }

        for (int i : cols) {
            for (int j = 0; j < row; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}
