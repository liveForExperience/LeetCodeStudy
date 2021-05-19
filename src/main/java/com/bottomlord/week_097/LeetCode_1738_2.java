package com.bottomlord.week_097;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/5/19 9:03
 */
public class LeetCode_1738_2 {
    public int kthLargestValue(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;

        if (row == 1 && col > 1) {
            for (int i = 1; i < col; i++) {
                matrix[0][i] ^= matrix[0][i - 1];
            }
        } else if (row > 1 && col == 1) {
            for (int i = 1; i < row; i++) {
                matrix[i][0] ^= matrix[i - 1][0];
            }
        } else {
            for (int i = 0; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] ^= matrix[i][j - 1];
                }
            }

            for (int i = 0; i < col; i++) {
                for (int j = 1; j < row; j++) {
                    matrix[j][i] ^= matrix[j - 1][i];
                }
            }
        }

        int[] arr = new int[row * col];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[index++] = matrix[i][j];
            }
        }

        Arrays.sort(arr);
        return arr[arr.length - k];
    }
}
