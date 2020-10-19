package com.bottomlord.week_067;

/**
 * @author ChenYue
 * @date 2020/10/19 9:04
 */
public class LeetCode_307_2 {
    class NumArray {
        private int[] nums;
        public NumArray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            this.nums = new int[nums.length];
            this.nums[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                this.nums[i] = this.nums[i - 1] + nums[i];
            }
        }

        public void update(int i, int val) {
            int diff = i == 0 ? -(nums[0] - val) : -(nums[i] - nums[i - 1] - val);
            for (int index = i; index < nums.length; index++) {
                nums[index] += diff;
            }
        }

        public int sumRange(int i, int j) {
            return i == 0 ? nums[j] : nums[j] - nums[i - 1];
        }
    }
}
