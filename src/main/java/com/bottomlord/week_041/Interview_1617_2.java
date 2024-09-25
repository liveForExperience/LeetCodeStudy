package com.bottomlord.week_041;

/**
 * @author ChenYue
 * @date 2020/4/16 13:01
 */
public class Interview_1617_2 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
            max = Math.max(nums[i], max);
        }

        return max;
    }
}