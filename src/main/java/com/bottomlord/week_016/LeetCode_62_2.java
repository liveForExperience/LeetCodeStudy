package com.bottomlord.week_016;

import java.util.Arrays;

public class LeetCode_62_2 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}