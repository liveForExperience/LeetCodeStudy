package com.bottomlord.week_053;

/**
 * @author ChenYue
 * @date 2020/7/7 18:34
 */
public class LeetCode_115_3 {
    public int numDistinct(String s, String t) {
        int sl = s.length(), tl = t.length();
        int[][] dp = new int[tl + 1][sl + 1];
        for (int i = 0; i <= sl; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= tl; i++) {
            for (int j = 1; j <= sl; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[tl][sl];
    }
}