package com.bottomlord.week_4;

import java.util.Arrays;

/**
 * @author LiveForExperience
 * @date 2019/7/31 20:51
 */
public class LeetCode_303_1_区域和检索_数组不可变 {
    class NumArray {
        private int[] nums;
        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }
            return sum;
        }
    }
}
