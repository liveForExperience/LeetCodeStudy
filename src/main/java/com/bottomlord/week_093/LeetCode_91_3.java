package com.bottomlord.week_093;

/**
 * @author ChenYue
 * @date 2021/4/21 10:03
 */
public class LeetCode_91_3 {
    public int numDecodings(String s) {
        int len = s.length();

        if (s.charAt(0) == '0') {
            return 0;
        }

        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }

            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) < '7') {
                if (i == 1) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[len - 1];
    }
}
