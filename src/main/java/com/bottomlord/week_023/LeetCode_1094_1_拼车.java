package com.bottomlord.week_023;

public class LeetCode_1094_1_拼车 {
    public boolean carPooling(int[][] trips, int capacity) {
        int max = 0;
        for (int[] trip : trips) {
            max = Math.max(max, trip[2]);
        }
        int[] nums = new int[max + 1];

        for (int[] trip : trips) {
            for (int i = trip[1]; i < trip[2]; i++) {
                nums[i] += trip[0];
            }
        }

        for (int num : nums) {
            if (num > capacity) {
                return false;
            }
        }

        return true;
    }
}
