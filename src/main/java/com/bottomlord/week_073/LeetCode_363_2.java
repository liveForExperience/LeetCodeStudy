package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/12/2 8:28
 */
public class LeetCode_363_2 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;

        for (int i = 0; i < col; i++) {
            int[] sum = new int[row];
            for (int j = i; j < col; j++) {
                for (int l = 0; l < row; l++) {
                    sum[l] += matrix[l][j];
                }

                max = Math.max(max, dpMax(sum, k));
            }
        }

        return max;
    }

    private int dpMax(int[] sum, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            int count = 0;
            for (int j = i; j < sum.length; j++) {
                count += sum[j];

                if (count <= k && count > max) {
                    max = count;
                }
            }
        }

        return max;
    }
}
