package com.bottomlord.week_246;

/**
 * @author chen yue
 * @date 2024-03-29 18:21:34
 */
public class LeetCode_2980_1_元素和最小的山形三元组I {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] lr = new int[n], rl = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
            lr[i] = min;
        }

        min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rl[i] = min;
        }

        min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > lr[i] && nums[i] > rl[i]) {
                min = Math.min(nums[i] + lr[i] + rl[i], min);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
