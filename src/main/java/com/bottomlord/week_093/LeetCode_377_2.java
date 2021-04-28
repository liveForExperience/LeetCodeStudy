package com.bottomlord.week_093;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_377_2 {
    public int combinationSum4(int[] nums, int target) {
        return dfs(nums, 0, target, new HashMap<>());
    }

    private int dfs(int[] nums, int sum, int target, Map<Integer, Integer> memo) {
        Integer count = memo.get(sum);
        if (count != null) {
            return count;
        }

        if (sum > target) {
            return 0;
        }

        if (sum == target) {
            return 1;
        }

        count = 0;
        for (int num : nums) {
            count += dfs(nums, sum + num, target, memo);
        }

        memo.put(sum, count);
        return count;
    }
}
