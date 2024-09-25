package com.bottomlord.week_127;

/**
 * @author chen yue
 * @date 2021-12-19 22:32:24
 */
public class LeetCode_offerII101_2 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1) {
            return false;
        }

        return backTrack(nums, 0, sum / 2, 0, new boolean[n][sum + 1]);
    }

    private boolean backTrack(int[] nums, int index, int target, int cur, boolean[][] memo) {
        if (cur > target) {
            return false;
        }

        if (cur == target) {
            return true;
        }

        if (index >= nums.length || memo[index][cur]) {
            return false;
        }

        for (int i = index; i < nums.length; i++) {
            cur += nums[i];
            boolean result = backTrack(nums, i + 1, target, cur, memo);
            if (result) {
                return true;
            }

            memo[i][cur] = true;

            cur -= nums[i];
        }

        return false;
    }
}
