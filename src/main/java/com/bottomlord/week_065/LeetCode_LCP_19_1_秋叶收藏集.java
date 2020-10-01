package com.bottomlord.week_065;

/**
 * @author ChenYue
 * @date 2020/10/1 21:04
 */
public class LeetCode_LCP_19_1_秋叶收藏集 {
    public int minimumOperations(String leaves) {
        int len = leaves.length();
        int[][] dp = new int[len][3];
        dp[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;

        for (int i = 1; i < len; i++) {
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0,
                isRed = leaves.charAt(i) == 'r' ? 1 : 0;

            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;

            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }

        return dp[len - 1][2];
    }
}
