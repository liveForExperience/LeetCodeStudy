package com.bottomlord.contest_20191005;

import java.util.Arrays;

public class Contest_4_验证回文字符串III {
    public boolean isValidPalindrome(String s, int k) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], -1);
        }
        int max = recurse(s, dp, 0, len - 1);
        return len - max <= k;
    }

    private int recurse(String s, int[][] dp, int i, int j) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int max = Math.max(recurse(s, dp, i + 1, j), recurse(s, dp, i, j - 1));
        if (s.charAt(i) == s.charAt(j)) {
            max = Math.max(max, recurse(s, dp, i + 1, j - 1) + 2);
        }

        return dp[i][j] = max;
    }

    public boolean isValidPalindrome2(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String s1 = sb.toString();

        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (s.charAt(i - 1) == s1.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return len - dp[len][len] <= k;
    }
}
