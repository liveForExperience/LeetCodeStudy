package com.bottomlord.week_007;

public class LeetCode_674_1_最长连续递增序列 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int len = 1, max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len++;
            } else {
                max = Math.max(len, max);
                len = 1;
            }
        }

        return Math.max(len, max);
    }
}
