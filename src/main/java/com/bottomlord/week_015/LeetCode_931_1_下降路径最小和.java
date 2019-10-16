package com.bottomlord.week_015;

public class LeetCode_931_1_下降路径最小和 {
    public int minFallingPathSum(int[][] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        System.arraycopy(A[0], 0, dp[0], 0, len);

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int left = j - 1 > 0 ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int right = j + 1 < len ? dp[i - 1][j + 1] : Integer.MAX_VALUE;
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(left, right)) + A[i][j];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(dp[len - 1][i], min);
        }

        return min;
    }
}
