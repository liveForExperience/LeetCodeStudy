package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-06 23:18:25
 */
public class LeetCode_2016_1_增量元素之间的最大差值 {
    public int maximumDifference(int[] nums) {
        int max = -1, min = nums[0], n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= min) {
                min = nums[i];
                continue;
            }

            max = Math.max(max, nums[i] - min);
        }
        return max;
    }
}
