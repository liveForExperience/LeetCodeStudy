package com.bottomlord.week_070;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/11/13 9:07
 */
public class LeetCode_334_1_递增的三元子序列 {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) {
            return false;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if (dp[i] >= 3) {
                return true;
            }
        }

        return false;
    }
}
