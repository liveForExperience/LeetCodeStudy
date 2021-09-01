package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-01 08:41:20
 */
public class LeetCode_1572_1_矩阵对角线元素的和 {
    public int diagonalSum(int[][] mat) {
        int n = mat.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i] + mat[i][n - i - 1];

            if (i == n - i - 1) {
                sum -= mat[i][i];
            }
        }

        return sum;
    }
}
