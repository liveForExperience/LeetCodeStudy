package com.bottomlord.week_007;

public class LeetCode_35_1_搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target || nums[i] > target) {
                return i;
            }
        }

        return nums.length;
    }
}
