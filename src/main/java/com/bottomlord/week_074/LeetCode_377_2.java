package com.bottomlord.week_074;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_377_2 {
    public int combinationSum4(int[] nums, int target) {
        return dfs(nums, target, new HashMap<>());
    }

    private int dfs(int[] nums, int target, Map<Integer, Integer> memo) {
        Integer count = memo.get(target);

        if (count != null) {
            return count;
        }

        if (target == 0) {
            return 1;
        }

        if (target < 0) {
            return 0;
        }

        count = 0;

        for (int num : nums) {
            if (num == target) {
                count++;
            } else {
                count += dfs(nums, target - num, memo);
            }
        }

        memo.put(target, count);
        return count;
    }
}
