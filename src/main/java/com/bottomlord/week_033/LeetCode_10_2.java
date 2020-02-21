package com.bottomlord.week_033;

/**
 * @author ThinkPad
 * @date 2020/2/21 12:59
 */
public class LeetCode_10_2 {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*' && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '*' && s.charAt(i - 1) != p.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 2];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[sLen][pLen];
    }
}
