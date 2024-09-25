package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 22:16:54
 */
public class LeetCode_offerII101_1_分割等和子集 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        return backTrack(nums, 0, sum / 2, 0);
    }

    private boolean backTrack(int[] nums, int index, int target, int cur) {
        if (cur > target) {
            return false;
        }

        if (cur == target) {
            return true;
        }

        for (int i = index; i < nums.length; i++) {
            cur += nums[i];
            boolean result = backTrack(nums, i + 1, target, cur);
            if (result) {
                return true;
            }
            cur -= nums[i];
        }

        return false;
    }
}
