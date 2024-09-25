package com.bottomlord.week_191;

/**
 * @author chen yue
 * @date 2023-03-06 19:28:58
 */
public class LeetCode_1653_2 {
    public int minimumDeletions(String s) {
        int n = s.length(), b = s.charAt(0) == 'b' ? 1 : 0;;
        char[] cs = s.toCharArray();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (cs[i] == 'a') {
                dp[i] = Math.max(dp[i - 1] + 1, b);
            } else {
                dp[i] = dp[i - 1];
                b++;
            }
        }

        return dp[n - 1];
    }
}