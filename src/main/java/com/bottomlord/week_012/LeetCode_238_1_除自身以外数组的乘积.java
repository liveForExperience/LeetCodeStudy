package com.bottomlord.week_012;

public class LeetCode_238_1_除自身以外数组的乘积 {
    public int[] productExceptSelf(int[] nums) {
        int[] lefts = new int[nums.length];
        lefts[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            lefts[i] = nums[i - 1] * lefts[i - 1];
        }

        int[] rights = new int[nums.length];
        rights[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rights[i] = nums[i + 1] * rights[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            lefts[i] *= rights[i];
        }
        return lefts;
    }
}
