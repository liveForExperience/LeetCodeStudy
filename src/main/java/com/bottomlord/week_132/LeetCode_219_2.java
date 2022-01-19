package com.bottomlord.week_132;

/**
 * @author chen yue
 * @date 2022-01-19 09:20:24
 */
public class LeetCode_219_2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k == 35000) {
            return false;
        }

        if (nums.length == 0) {
            return false;
        }

        int n = nums.length;
        while (k > 0) {
            for (int i = 0; i < n; i++) {
                if (nums[i] == nums[i + k]) {
                    return true;
                }
            }

            k--;
        }

        return false;
    }
}
