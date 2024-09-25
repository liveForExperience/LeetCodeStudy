package com.bottomlord.week_130;

/**
 * @author chen yue
 * @date 2022-01-05 21:57:52
 */
public class LeetCode_2006_1_差的绝对值为K的数对数目 {
    public int countKDifference(int[] nums, int k) {
        int n = nums.length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    count++;
                }
            }
        }

        return count;
    }
}
