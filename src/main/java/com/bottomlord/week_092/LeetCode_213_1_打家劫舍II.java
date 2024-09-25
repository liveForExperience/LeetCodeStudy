package com.bottomlord.week_092;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/4/15 8:41
 */
public class LeetCode_213_1_打家劫舍II {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        if (len < 3) {
            return Arrays.stream(nums).max().getAsInt();
        }

        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
                        doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}
