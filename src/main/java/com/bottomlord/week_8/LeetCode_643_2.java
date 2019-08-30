package com.bottomlord.week_8;

public class LeetCode_643_2 {
    public double findMaxAverage(int[] nums, int k) {
        int pre = 0;
        for (int i = 0; i < k; i++) {
            pre += nums[i];
        }
        int max = pre;
        for (int i = 1; i <= nums.length - k; i++) {
            pre = pre - nums[i - 1] + nums[i + k - 1];
            max = Math.max(pre, max);
        }
        return max * 1D / k;
    }
}