package com.bottomlord.week_050;

/**
 * @author ChenYue
 * @date 2020/6/15 8:28
 */
public class LeetCode_41_1_缺失的第一个正数 {
    public int firstMissingPositive(int[] nums) {
        boolean hasOne = false;
        int len = nums.length;
        for (int num : nums) {
            if (num == 1) {
                hasOne = true;
                break;
            }
        }

        if (!hasOne) {
            return 1;
        }

        if (len == 1) {
            return 2;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int num = Math.abs(nums[i]);

            if (num < len) {
                nums[num] = -Math.abs(nums[num]);
            }

            if (num == len) {
                nums[0] = -Math.abs(nums[0]);
            }
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) {
            return len;
        }

        return len + 1;
    }
}
