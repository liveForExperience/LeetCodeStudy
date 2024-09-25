package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/24 7:56
 */
public class Interview_1716_1_按摩师 {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;

        int[][] dp = new int[len][2];
        dp[0][1] = nums[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }
}
