package com.bottomlord.week_014;

import java.util.Arrays;

public class LeetCode_215_1_数组中的第k个最大元素 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int index = nums.length;
        for (int i = 0; i < k; i++) {
            index -= 1;
        }
        return nums[index];
    }
}
