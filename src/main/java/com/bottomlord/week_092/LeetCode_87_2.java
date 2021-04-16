package com.bottomlord.week_092;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2021/4/16 11:20
 */
public class LeetCode_87_2 {
    public boolean isScramble(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) {
            return false;
        }

        if (len1 == 1) {
            return Objects.equals(s1, s2);
        }

        boolean[][][] dp = new boolean[len1][len1][len1 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len1; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int l = 2; l <= len1; l++) {
            for (int i = 0; i <= len1 - l; i++) {
                for (int j = 0; j <= len2 - l; j++) {
                    for (int k = 1; k < l; k++) {
                        if ((dp[i][j][k] && dp[i + k][j + k][l - k]) ||
                            (dp[i][j + l - k][k] && dp[i + k][j][l - k])) {
                            dp[i][j][l] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][len1];
    }
}
