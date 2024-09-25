package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-09-27 08:35:06
 */
public class LeetCode_639_1_解码方法II {
    private int mod = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        s = " " + s;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i) == '*') {
                dp[i] = 9 * dp[i - 1];
            } else if (s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }

            if (s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == '*') {
                dp[i] += 15 * dp[i - 2];
            } else if (s.charAt(i) == '*') {
                if (s.charAt(i - 1) == '1') {
                    dp[i] += 9 * dp[i - 2];
                } else if (s.charAt(i - 1) == '2') {
                    dp[i] += 6 * dp[i - 2];
                }
            } else if (s.charAt(i - 1) == '*') {
                int a = s.charAt(i) - '0';
                if (a >= 7 && a <= 9) {
                    dp[i] += dp[i - 2];
                } else if (a >= 0 && a <= 6) {
                    dp[i] += 2 * dp[i - 2];
                }
            } else {
                int a = s.charAt(i) - '0', b = (s.charAt(i - 1) - '0') * 10 + a;
                if (b >= 10 && b <= 26) {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return (int)dp[n];
    }
}
