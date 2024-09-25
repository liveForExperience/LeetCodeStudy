package com.bottomlord.week_093;

/**
 * @author ChenYue
 * @date 2021/4/22 8:31
 */
public class LeetCode_363_1_矩形区域不超过K的最大数值和 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, ans = Integer.MIN_VALUE;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                int[][] dp = new int[row + 1][col + 1];
                for (int r = i; r <= row; r++) {
                    for (int c = j; c <= col; c++) {
                        dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + matrix[r][c];

                        if (dp[r][c] <= k && dp[r][c] > ans) {
                            ans = dp[r][c];
                        }
                    }
                }
            }
        }
        return ans;
    }
}
