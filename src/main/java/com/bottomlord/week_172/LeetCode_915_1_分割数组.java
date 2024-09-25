package com.bottomlord.week_172;

/**
 * @author chen yue
 * @date 2022-10-24 08:53:39
 */
public class LeetCode_915_1_分割数组 {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int[] lefts = new int[n], rights = new int[n];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            lefts[i] = max;
        }

        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            rights[i] = min;
        }

        for (int i = 1; i < n; i++) {
            if (lefts[i - 1] <= rights[i]) {
                return i;
            }
        }

        return n;
    }
}
