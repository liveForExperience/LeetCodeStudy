package com.bottomlord.week_116;

/**
 * @author chen yue
 * @date 2021-09-27 22:07:44
 */
public class LeetCode_91_1_解码方法 {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        s = " " + s;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = s.charAt(i) - '0', b = (s.charAt(i - 1) - '0') * 10 + a;
            if (a >= 1 && a <= 9) {
                dp[i] = dp[i - 1];
            }

            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
