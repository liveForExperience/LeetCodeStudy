package com.bottomlord.week_105;

public class LeetCode_offer42_2 {
    public int maxSubArray(int[] nums) {
        int status = nums[0], max = status;
        for (int i = 1; i < nums.length; i++) {
            status = Math.max(status + nums[i], nums[i]);
            max = Math.max(status, max);
        }
        return max;
    }
}