package com.bottomlord.week_109;

/**
 * @author chen yue
 * @date 2021-08-15 14:21:53
 */
public class LeetCode_1480_1_一堆数组的动态和 {
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }

        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        return sums;
    }
}
