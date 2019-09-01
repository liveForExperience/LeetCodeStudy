package com.bottomlord.week_8;

public class LeetCode_581_2 {
    public int findUnsortedSubarray(int[] nums) {
        int end = -1, max = nums[0], start = -1, min = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
        }

        if (end == -1) {
            return 0;
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                start = i;
            }
        }

        return end - start + 1;
    }
}