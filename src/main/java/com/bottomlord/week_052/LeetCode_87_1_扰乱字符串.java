package com.bottomlord.week_052;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/7/3 8:26
 */
public class LeetCode_87_1_扰乱字符串 {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n != m) {
            return false;
        }

        if (n == 1) {
            return Objects.equals(s1, s2);
        }

        boolean[][][] dp = new boolean[n][n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int l = 2; l <= n; l++) {
            for (int i1 = 0; i1 < n - l; i1++) {
                for (int i2 = 0; i2 < m - l; i2++) {
                    for (int k = 1; k < l; k++) {
                        if (dp[i1][i2][k] && dp[i1 + k][i2 + k][l - k]) {
                            dp[i1][i2][l] = true;
                            break;
                        }

                        if (dp[i1][i2 + l - k][k] && dp[i1 + k][i2][l - k]) {
                            dp[i1][i2][l] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }
}
