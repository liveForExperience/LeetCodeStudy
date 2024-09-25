package com.bottomlord.week_054;

/**
 * @author ChenYue
 * @date 2020/7/14 8:31
 */
public class LeetCode_132_2 {
    public int minCut(String s) {
        int len = s.length();
        if (len < 2) {
            return 0;
        }

        boolean[][] isValid = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(right) == s.charAt(left) && (right - left <= 2 || isValid[left + 1][right - 1])) {
                    isValid[left][right] = true;
                }
            }
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }

        for (int i = 1; i < len; i++) {
            if (isValid[0][i]) {
                dp[i] = 0;
            }

            for (int j = 0; j < i; j++) {
                if (isValid[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }
}