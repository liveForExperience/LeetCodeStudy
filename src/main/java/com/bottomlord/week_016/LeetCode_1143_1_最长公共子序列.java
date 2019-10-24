package com.bottomlord.week_016;

public class LeetCode_1143_1_最长公共子序列 {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                dp[i][j] = max3(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1] + (text1.charAt(i - 1) == text2.charAt(j - 1) ? 1 : 0));
            }
        }

        return dp[len1][len2];
    }

    private int max3(int x, int y, int z) {
        return Math.max(x, Math.max(y, z));
    }
}
