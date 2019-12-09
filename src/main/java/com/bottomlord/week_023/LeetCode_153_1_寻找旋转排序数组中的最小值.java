package com.bottomlord.week_023;

public class LeetCode_153_1_寻找旋转排序数组中的最小值 {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int first = nums[0], pre = first;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < pre) {
                return nums[i];
            }

            pre = nums[i];
        }

        return first;
    }
}
