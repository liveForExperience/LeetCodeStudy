package com.bottomlord.week_017;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode_73_1_矩阵置零 {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int num : rows) {
            Arrays.fill(matrix[num], 0);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int num : cols) {
                matrix[i][num] = 0;
            }
        }
    }
}
