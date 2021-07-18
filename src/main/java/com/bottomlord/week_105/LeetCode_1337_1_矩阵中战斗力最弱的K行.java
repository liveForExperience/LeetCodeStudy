package com.bottomlord.week_105;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode_1337_1_矩阵中战斗力最弱的K行 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length;
        int[][] matrix = new int[n][2];
        for (int row = 0; row < mat.length; row++) {
            int count = 0;
            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] == 0) {
                    break;
                }
                count++;
            }

            matrix[row][0] = count;
            matrix[row][1] = row;
        }

        Arrays.sort(matrix, Comparator.comparingInt(o -> o[0]));

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = matrix[i][1];
        }
        return ans;
    }
}
