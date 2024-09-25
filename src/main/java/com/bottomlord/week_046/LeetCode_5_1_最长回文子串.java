package com.bottomlord.week_046;

/**
 * @author ChenYue
 * @date 2020/5/21 8:23
 */
public class LeetCode_5_1_最长回文子串 {
    public String longestPalindrome(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        String ans = "";
        for (int l = 0; l < len; l++) {
            for (int i = 0; i < len; i++) {
                int j = i + l;
                if (j >= len) {
                    break;
                }

                if (l == 0) {
                    dp[i][j] = true;
                } else {
                    boolean equals = s.charAt(i) == s.charAt(j);
                    if (l == 1) {
                        dp[i][j] = equals;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] && equals;
                    }
                }

                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }

        return ans;
    }
}
