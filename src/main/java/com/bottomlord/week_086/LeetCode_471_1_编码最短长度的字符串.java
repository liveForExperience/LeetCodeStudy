package com.bottomlord.week_086;

/**
 * @author ChenYue
 * @date 2021/3/1 8:34
 */
public class LeetCode_471_1_编码最短长度的字符串 {
    public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][i + len - 1] = getEncodeString(dp, s, i, i + len - 1);

                for (int k = i; k < i + len - 1; k++) {
                    String str = dp[i][k] + dp[k + 1][i + len - 1];
                    if (str.length() < dp[i][i + len - 1].length()) {
                        dp[i][i + len - 1] = str;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    private String getEncodeString(String[][] dp, String s, int i, int j) {
        s = s.substring(i, j + 1);
        if (s.length() < 5) {
            return s;
        }

        int p = (s + s).indexOf(s, 1);
        if (p < s.length()) {
            int count = s.length() / p;
            return count + "[" + dp[i][i + p - 1] + "]";
        }
        return s;
    }
}
