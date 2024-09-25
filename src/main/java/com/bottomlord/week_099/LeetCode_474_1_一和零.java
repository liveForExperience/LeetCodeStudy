package com.bottomlord.week_099;

public class LeetCode_474_1_一和零 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] counts = new int[len + 1][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            counts[i + 1][0] = zero;
            counts[i + 1][1] = one;
        }

        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            int zero = counts[i][0], one = counts[i][1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int a = dp[i - 1][j][k];
                    int b = (j >= zero && k >= one) ? dp[i - 1][j - zero][k - one] + 1 : 0;
                    dp[i][j][k] = Math.max(a, b);
                }
            }
        }

        return dp[len][m][n];
    }
}
