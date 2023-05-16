package com.bottomlord.week_201;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-05-16 22:45:24
 */
public class LeetCode_1335_1_工作计划的最低难度 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[][] dp = new int[d + 1][n + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[1][i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    dp[i][j] = Math.min(dp[i][j], max + dp[i - 1][k - 1]);
                }
            }
        }

        return dp[d][n];
    }
}
