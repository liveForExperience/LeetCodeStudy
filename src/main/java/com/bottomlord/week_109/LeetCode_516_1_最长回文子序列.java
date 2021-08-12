package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-12 22:16:20
 */
public class LeetCode_516_1_最长回文子序列 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char x = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(j) == x) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
