package com.bottomlord.week_034;

/**
 * @author ThinkPad
 * @date 2020/2/29 17:30
 */
public class Interview_42_2 {
    public int maxSubArray(int[] nums) {
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] > 0 ? nums[i - 1] + nums[i] : nums[i];
            max = Math.max(nums[i], max);
        }

        return max;
    }
}