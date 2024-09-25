package com.bottomlord.week_052;

/**
 * @author ChenYue
 * @date 2020/7/5 17:32
 */
public class LeetCode_91_2 {
    public int numDecodings(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        if (len == 0) {
            return 0;
        }

        char[] cs = s.toCharArray();
        if (isValid(cs[0] - '0')) {
            return 0;
        }

        if (len == 1) {
            int num = cs[0] - '0';
            return isValid(num) ? 1 : 0;
        }

        int[] dp = new int[len + 1];
        dp[1] = 1;
        dp[2] = (isValid(cs[1] - '0') ? dp[1] : 0) + (isValid(twoCharNum(cs[0], cs[1])) ? 1 : 0);

        for (int i = 3; i <= len; i++) {
            if (isValid(cs[i - 1] - '0')) {
                dp[i] = dp[i - 1];
            }

            if (isValid(cs[i - 2] - '0') && isValid(twoCharNum(cs[i - 2], cs[i - 1]))) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[len];
    }

    private boolean isValid(int num) {
        return num > 0 && num <= 26;
    }

    private int twoCharNum(char a, char b) {
        return 10 * (a - '0') + (b - '0');
    }
}
