package com.bottomlord.week_008;

public class LeetCode_219_3 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length == 0) {
            return false;
        }

        while (k > 0) {
            for (int i = 0; i < nums.length - k; i++) {
                if (nums[i] == nums[i + k]) {
                    return true;
                }
            }

            k--;
        }

        return false;
    }
}