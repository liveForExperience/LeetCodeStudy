package com.bottomlord.week_059;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author ChenYue
 * @date 2020/8/17 8:21
 */
public class LeetCode_213_1_打家劫舍II {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        if (len == 1) {
            return nums[0];
        }

        if (len <= 3) {
            Arrays.sort(nums);
            return nums[len - 1];
        }

        return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)), doRob(Arrays.copyOfRange(nums, 1, len)));
    }

    private int doRob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[2] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[len - 1];
    }
}
