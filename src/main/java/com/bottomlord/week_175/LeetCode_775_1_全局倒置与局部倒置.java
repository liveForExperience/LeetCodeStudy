package com.bottomlord.week_175;

/**
 * @author chen yue
 * @date 2022-11-18 07:35:53
 */
public class LeetCode_775_1_全局倒置与局部倒置 {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length, max = nums[0];
        for (int i = 2; i < n; i++) {
            if (nums[i] < max) {
                return false;
            }

            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }
}
