package com.bottomlord.week_152;

/**
 * @author chen yue
 * @date 2022-06-11 00:11:53
 */
public class LeetCode_730_1_统计不同回文子序列 {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int mod = 1000000007;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    int l = i + 1, r = j - 1;

                    while (l <= r && s.charAt(i) != s.charAt(l)) {
                        l++;
                    }

                    while (l <= r && s.charAt(i) != s.charAt(r)) {
                        r--;
                    }

                    if (l > r) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
                    } else if (l == r) {
                        dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 2 * dp[i + 1][j - 1] - dp[l + 1][r - 1];
                    }
                }

                dp[i][j] = dp[i][j] >= 0 ? dp[i][j] % mod : dp[i][j] + mod;
            }
        }

        return dp[0][n - 1];
    }
}
