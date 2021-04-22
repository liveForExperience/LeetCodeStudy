package com.bottomlord.week_093;

/**
 * @author ChenYue
 * @date 2021/4/22 16:02
 */
public class LeetCode_363_2 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int[][] sums = new int[row][col];
        for (int i = 0; i < row; i++) {
            sums[i][0] = matrix[i][0];
            for (int j = 1; j < col; j++) {
                sums[i][j] = sums[i][j - 1] + matrix[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            for (int j = i; j < col; j++) {
                int[] colSums = new int[row];
                for (int r = 0; r < row; r++) {
                    colSums[r] = sums[r][j] - sums[r][i] + matrix[r][i];
                }

                ans = Math.max(ans, getMax(colSums, k));
            }
        }

        return ans;
    }

    private int getMax(int[] sums, int k) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int sum = 0;
            for (int j = i; j < sums.length; j++) {
                sum += sums[j];
                if (sum <= k && sum > max) {
                    max = sum;
                }
            }
        }

        return max;
    }
}