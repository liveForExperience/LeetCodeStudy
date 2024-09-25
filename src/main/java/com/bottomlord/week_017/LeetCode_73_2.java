package com.bottomlord.week_017;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode_73_2 {
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

        for (int i = 0; i < matrix.length; i++) {
            if (rows.contains(i)) {
                Arrays.fill(matrix[i], 0);
                continue;
            }
            for (int j = 0; j < matrix[i].length; j++) {
                if (cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}