package com.bottomlord.week_192;

/**
 * @author chen yue
 * @date 2023-03-14 08:42:57
 */
public class LeetCode_1605_1_给定行和列的和求可行矩阵 {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int r = rowSum.length, c = colSum.length;
        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int diff = Math.min(rowSum[i], colSum[j]);
                matrix[i][j] = diff;
                rowSum[i] -= diff;
                colSum[j] -= diff;
            }
        }

        return matrix;
    }
}
