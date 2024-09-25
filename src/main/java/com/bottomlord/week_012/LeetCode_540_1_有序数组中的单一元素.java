package com.bottomlord.week_012;

public class LeetCode_540_1_有序数组中的单一元素 {
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
