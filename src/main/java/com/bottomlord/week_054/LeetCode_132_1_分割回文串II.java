package com.bottomlord.week_054;

/**
 * @author ChenYue
 * @date 2020/7/13 8:54
 */
public class LeetCode_132_1_分割回文串II {
    public int minCut(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < len; i++) {
            if (isValid(s, 0, i)) {
                dp[i] = 0;
                continue;
            }

            for (int j = 0; j < i; j++) {
                if (isValid(s, j + 1, i)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }

    private boolean isValid(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
