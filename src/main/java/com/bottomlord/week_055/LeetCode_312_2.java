package com.bottomlord.week_055;

import com.bottomlord.week_011.LeetCode_131_2;

/**
 * @author ChenYue
 * @date 2020/7/20 9:04
 */
public class LeetCode_312_2 {
    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[] val = new int[len + 2];
        val[0] = val[len + 1] = 1;
        for (int i = 1; i<= len; i++) {
            val[i] = nums[i - 1];
        }

        int[][] dp = new int[len + 2][len + 2];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 2; j < len + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], val[i] * val[k] * val[j] + dp[i][k] + dp[k][j]);
                }
            }
        }

        return dp[0][len + 1];
    }
}
