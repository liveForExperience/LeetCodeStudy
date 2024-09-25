package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/12/2 8:46
 */
public class LeetCode_363_3 {
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
        int rollSum = sum[0], max = rollSum;
        for (int i = 1; i < sum.length; i++) {
            if (rollSum > 0) {
                rollSum += sum[i];
            } else {
                rollSum = sum[i];
            }

            if (rollSum > max) {
                max = rollSum;
            }
        }

        if (max <= k) {
            return max;
        }

        max = Integer.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            int count = 0;
            for (int j = i; j < sum.length; j++) {
                count += sum[j];

                if (count == k) {
                    return k;
                }

                if (count < k && count > max) {
                    max = count;
                }
            }
        }

        return max;
    }
}
