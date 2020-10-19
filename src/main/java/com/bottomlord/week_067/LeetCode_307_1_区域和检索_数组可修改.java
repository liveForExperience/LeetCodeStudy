package com.bottomlord.week_067;

/**
 * @author ChenYue
 * @date 2020/10/19 8:57
 */
public class LeetCode_307_1_区域和检索_数组可修改 {
    class NumArray {
        private int[] nums;
        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int i, int val) {
            nums[i] = val;
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            for (int index = i; index <= j; index++) {
                sum += nums[index];
            }
            return sum;
        }
    }
}
