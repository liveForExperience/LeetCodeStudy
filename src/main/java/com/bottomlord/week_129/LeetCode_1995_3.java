package com.bottomlord.week_129;

/**
 * @author chen yue
 * @date 2021-12-29 22:03:01
 */
public class LeetCode_1995_3 {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n][401][4];
        dp[0][0][0] = 1;
        for (int i = 1; i < n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j < 401; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] += dp[i - 1][j][k];
                    if (j - num >= 0 && k - 1 >= 0) {
                        dp[i][j][k] += dp[i - 1][j - num][k - 1];
                    }
                }
            }
        }

        int count = 0;
        for (int i = 3; i < n; i++) {
            count += dp[i][nums[i]][3];
        }
        return count;
    }
}