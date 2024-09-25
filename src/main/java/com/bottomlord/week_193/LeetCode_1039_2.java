package com.bottomlord.week_193;

/**
 * @author chen yue
 * @date 2023-04-02 12:25:14
 */
public class LeetCode_1039_2 {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    ans = Math.min(dp[i][k] + dp[k][j] + values[i] * values[k] * values[j], ans);
                }
                dp[i][j] = ans;
            }
        }

        return dp[0][n - 1];
    }
}
