package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/16 8:11
 */
public class LeetCode_44_1_通配符匹配 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length(), pLen = p.length();
        if (s.equals(p)) {
            return true;
        }

        if ("*".equals(p)) {
            return true;
        }

        if (sLen == 0 || pLen == 0) {
            return false;
        }

        boolean[][] dp = new boolean[pLen + 1][sLen + 1];
        dp[0][0] = true;

        for (int i = 1; i < pLen + 1; i++) {
            int j = 1;
            if (p.charAt(i - 1) == '*') {
                while (!dp[i - 1][j - 1] && j < sLen + 1) {
                    j++;
                }

                dp[i][j - 1] = dp[i - 1][j - 1];

                while (j < sLen + 1) {
                    dp[i][j++] = true;
                }
            } else if (p.charAt(i - 1) == '?') {
                for (; j < sLen + 1; j++) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            } else {
                for (; j < sLen + 1; j++) {
                    dp[i][j] = dp[i - 1][j - 1] && p.charAt(i - 1) == s.charAt(j - 1);
                }
            }
        }

        return dp[pLen][sLen];
    }
}
