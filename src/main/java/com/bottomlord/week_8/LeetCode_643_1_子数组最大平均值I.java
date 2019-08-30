package com.bottomlord.week_8;

public class LeetCode_643_1_子数组最大平均值I {
    public double findMaxAverage(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        Integer pre = null;
        for (int i = 0; i <= nums.length - k; i++) {
            if (pre == null) {
                pre = 0;
                int tmp = k;
                while (tmp > 0) {
                    pre += nums[i + tmp-- - 1];
                }
            } else {
                pre = pre - nums[i - 1] + nums[i + k - 1];
            }
            max = Math.max(max, pre);
        }
        return max * 1D / k;
    }
}
