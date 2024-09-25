package com.bottomlord.week_093;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/4/25 8:50
 */
public class LeetCode_377_3 {
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.sort(nums);
        return dfs(nums, memo, target);
    }

    private int dfs(int[] nums, int[] memo, int target) {
        if (target < 0) {
            return 0;
        }

        if (target == 0) {
            return 1;
        }

        if (memo[target] >= 0) {
            return memo[target];
        }

        int count = 0;
        for (int num : nums) {
            count += dfs(nums, memo, target - num);
        }

        memo[target] = count;
        return count;
    }
}
