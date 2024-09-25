package com.bottomlord.week_049;

/**
 * @author ChenYue
 * @date 2020/6/12 8:29
 */
public class LeetCode_32_2 {
    public int longestValidParentheses(String s) {
        int len = s.length(), ans = 0;
        int[] dp = new int[len];

        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }

                ans = Math.max(dp[i], ans);
            }
        }

        return ans;
    }
}