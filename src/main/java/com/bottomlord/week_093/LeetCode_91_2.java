package com.bottomlord.week_093;

/**
 * @author ChenYue
 * @date 2021/4/21 9:40
 */
public class LeetCode_91_2 {
    public int numDecodings(String s) {
        int len = s.length();

        if (s.startsWith("0")) {
            return 0;
        }

        if (len <= 1) {
            return len;
        }

        int[] dp = new int[len + 1];
        dp[1] = 1;
        dp[2] = (isValid("" + s.charAt(0)) ? dp[1] : 0) + (isValid("" + s.charAt(0) + s.charAt(1)) ? 1 : 0);

        for (int i = 3; i <= len; i++) {
            if (isValid("" + s.charAt(i - 1))) {
                dp[i] = dp[i - 1];
            }

            if (isValid("" + s.charAt(i - 2) + s.charAt(i - 1))) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[len];
    }

    private boolean isValid(String s) {
        if (s.length() > 2 || s.length() < 1) {
            return false;
        }

        char c = s.charAt(0);
        if (s.length() == 1) {
            return c <= '9' && c >= '1';
        }

        if (c != '1' && c != '2') {
            return false;
        }

        char c2 = s.charAt(1);
        if (c == '1') {
            return c2 >= '0' && c2 <= '9';
        }

        return c2 >= '0' && c2 <= '6';
    }
}
