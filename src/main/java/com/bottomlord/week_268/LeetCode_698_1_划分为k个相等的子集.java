package com.bottomlord.week_268;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-08-26 13:19:25
 */
public class LeetCode_698_1_划分为k个相等的子集 {
    private int[] bucket, nums;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        int sum = 0;
        for (int num : this.nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        for (int num : this.nums) {
            if (num > target) {
                return false;
            }
        }

        this.bucket = new int[k];
        Arrays.sort(this.nums);
        int n = this.nums.length;
        for (int i = 0; i < n / 2; i++) {
            int tmp = this.nums[i];
            this.nums[i] = this.nums[this.nums.length - 1 - i];
            this.nums[this.nums.length - 1 - i] = tmp;
        }

        return backTrack(0, target);
    }

    private boolean backTrack(int index, int target) {
        if (index >= this.nums.length) {
            return true;
        }

        int num = this.nums[index];
        for (int i = 0; i < this.bucket.length; i++) {
            if (i < this.bucket.length - 1 && bucket[i] == bucket[i + 1]) {
                continue;
            }

            if (bucket[i] + num > target) {
                continue;
            }

            bucket[i] += num;
            boolean result = backTrack(index + 1, target);
            if (result) {
                return true;
            }
            bucket[i] -= num;
        }

        return false;
    }
}
