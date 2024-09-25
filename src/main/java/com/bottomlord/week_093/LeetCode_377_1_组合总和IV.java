package com.bottomlord.week_093;

public class LeetCode_377_1_组合总和IV {
    public int combinationSum4(int[] nums, int target) {
        return backTrack(nums, 0, target);
    }

    private int backTrack(int[] nums, int sum, int target) {
        if (sum > target) {
            return 0;
        }

        if (sum == target) {
            return 1;
        }

        int ans = 0;
        for (int num : nums) {
            ans += backTrack(nums, sum + num, target);
        }
        return ans;
    }
}
