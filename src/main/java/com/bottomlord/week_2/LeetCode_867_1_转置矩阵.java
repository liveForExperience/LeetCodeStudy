package com.bottomlord.week_2;

/**
 * @author LiveForExperience
 * @date 2019/7/19 12:52
 */
public class LeetCode_867_1_转置矩阵 {
    public int[][] transpose(int[][] A) {
        if (A == null) {
            return null;
        }

        int[][] B = new int[A[0].length][A.length];

        for (int i = 0 ; i < A.length; i++) {
            for (int j = i + 1; j < A[i].length; j++) {
                B[j][i] = A[i][j];
                B[i][j] = A[j][i];
            }
        }

        return B;
    }
}
