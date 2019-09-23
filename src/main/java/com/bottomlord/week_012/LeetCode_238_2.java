package com.bottomlord.week_012;

import java.util.Arrays;

public class LeetCode_238_2 {
    public int[] productExceptSelf(int[] nums) {
        int left = 1, right = 1, len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, 1);
        for (int i = 0; i < len; i++) {
            ans[i] *= left;
            left *= nums[i];

            ans[len - i - 1] *= right;
            right *= nums[len - i - 1];
        }
        return ans;
    }
}