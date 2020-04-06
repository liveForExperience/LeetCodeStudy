package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/6 20:24
 */
public class LeetCode_42_1_编辑距离 {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int add1 = dp[i - 1][j] + 1, add2 = dp[i][j - 1] + 1;
                int edit = word1.charAt(i) == word2.charAt(j) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(add1, Math.min(add2, edit));
            }
        }

        return dp[len1][len2];
    }
}
