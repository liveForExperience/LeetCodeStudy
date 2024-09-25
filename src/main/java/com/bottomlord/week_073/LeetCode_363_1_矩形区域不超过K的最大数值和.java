package com.bottomlord.week_073;

/**
 * @author ChenYue
 * @date 2020/11/30 17:24
 */
public class LeetCode_363_1_矩形区域不超过K的最大数值和 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int[][] dp = new int[row + 1][col + 1];
                for (int r = i; r <= row; r++) {
                    for (int c = j; c <= col; c++) {
                        dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r - 1][c - 1];
                        if (dp[r][c] <= k && dp[r][c] > max) {
                            max = dp[r][c];
                        }
                    }
                }
            }
        }

        return max;
    }
}
