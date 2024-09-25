package com.bottomlord.week_025;

public class LeetCode_486_1_预测赢家 {
    public boolean PredictTheWinner(int[] nums) {
        return dfs(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int dfs(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return turn * nums[start];
        }

        int left = turn * nums[start] + dfs(nums, start + 1, end, -turn);
        int right = turn * nums[end] + dfs(nums, start, end - 1, -turn);

        return turn * Math.max(turn * left, turn * right);
    }
}
