package com.bottomlord.week_074;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_377_1_组合总和IV {
    private int count = 0;
    public int combinationSum4(int[] nums, int target) {
        backTrack(nums, 0, target);
        return count;
    }

    private void backTrack(int[] nums, int sum, int target) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            count++;
            return;
        }

        Set<Integer> memo = new HashSet<>();
        for (int num : nums) {
            if (memo.contains(num)) {
                continue;
            }

            memo.add(num);
            backTrack(nums, sum + num, target);
        }
    }
}
