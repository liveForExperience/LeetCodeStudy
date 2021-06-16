package com.bottomlord.week_101;

/**
 * @author ChenYue
 * @date 2021/6/16 8:29
 */
public class LeetCode_486_1_预测赢家 {
    public boolean PredictTheWinner(int[] nums) {
        return recuse(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int recuse(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }

        int pickStart = nums[start] * turn + recuse(nums, start + 1, end, -turn),
            pickEnd = nums[end] * turn + recuse(nums, start, end - 1, -turn);

        return turn == 1 ? Math.max(pickEnd, pickStart) : Math.min(pickStart, pickEnd);
    }
}
