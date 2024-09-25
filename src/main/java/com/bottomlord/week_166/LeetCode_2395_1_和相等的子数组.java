package com.bottomlord.week_166;

/**
 * @author chen yue
 * @date 2022-09-14 08:05:24
 */
public class LeetCode_2395_1_和相等的子数组 {
    public boolean findSubarrays(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (sum == nums[j] + nums[j + 1]) {
                    return true;
                }
            }
        }

        return false;
    }
}
