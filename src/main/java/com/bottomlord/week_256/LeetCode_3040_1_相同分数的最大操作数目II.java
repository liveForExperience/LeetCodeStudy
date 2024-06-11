package com.bottomlord.week_256;

/**
 * @author chen yue
 * @date 2024-06-08 14:04:39
 */
public class LeetCode_3040_1_相同分数的最大操作数目II {
    public int maxOperations(int[] nums) {
        int n = nums.length;
        return Math.max(recurse(nums, 2, n - 1, nums[0] + nums[1], getMemo(n)),
                Math.max(recurse(nums, 1, n - 2, nums[0] + nums[n - 1], getMemo(n)),
                        recurse(nums, 0, n - 3, nums[n - 1] + nums[n - 2], getMemo(n)))) + 1;
    }

    private int recurse(int[] nums, int head, int tail, int sum, int[][] memo) {
        if (head >= tail) {
            return 0;
        }

        if (memo[head][tail] != -1) {
            return memo[head][tail];
        }

        int max = 0;
        if (nums[head] + nums[head + 1] == sum) {
            max = Math.max(max, recurse(nums, head + 2, tail, sum, memo) + 1);
        }

        if (nums[head] + nums[tail] == sum) {
            max = Math.max(max, recurse(nums, head + 1, tail - 1, sum, memo) + 1);
        }

        if (nums[tail] + nums[tail - 1] == sum) {
            max = Math.max(max, recurse(nums, head, tail - 2, sum, memo) + 1);
        }

        memo[head][tail] = max;
        return max;
    }

    private int[][] getMemo(int n) {
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        return memo;
    }
}
