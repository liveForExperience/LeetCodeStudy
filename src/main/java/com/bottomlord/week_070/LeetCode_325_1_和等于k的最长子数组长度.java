package com.bottomlord.week_070;

/**
 * @author ChenYue
 * @date 2020/11/9 8:27
 */
public class LeetCode_325_1_和等于k的最长子数组长度 {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len];

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (sums[j] - sums[i] + nums[i] == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
