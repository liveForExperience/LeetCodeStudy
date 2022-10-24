package com.bottomlord.week_172;

/**
 * @author chen yue
 * @date 2022-10-24 09:15:15
 */
public class LeetCode_915_2 {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int[] rights = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rights[i] = min;
        }

        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, nums[i]);
            if (max <= rights[i + 1]) {
                return i + 1;
            }
        }

        return n;
    }
}