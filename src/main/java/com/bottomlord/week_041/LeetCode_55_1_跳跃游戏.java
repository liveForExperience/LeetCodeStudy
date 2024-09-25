package com.bottomlord.week_041;

/**
 * @author ChenYue
 * @date 2020/4/17 8:00
 */
public class LeetCode_55_1_跳跃游戏 {
    public boolean canJump(int[] nums) {
        return dfs(0, nums, new boolean[nums.length]);
    }

    private boolean dfs(int index, int[] nums, boolean[] memo) {
        if (index >= nums.length || memo[index]) {
            return false;
        }

        if (index == nums.length - 1) {
            return true;
        }

        if (nums[index] == 0) {
            return false;
        }

        memo[index] = true;

        boolean res = false;
        for (int i = 1; i <= nums[index]; i++) {
            res |= dfs(index + i, nums, memo);
        }

        return res;
    }
}
