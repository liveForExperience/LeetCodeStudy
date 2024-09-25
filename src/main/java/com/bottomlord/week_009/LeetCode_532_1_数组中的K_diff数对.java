package com.bottomlord.week_009;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_532_1_数组中的K_diff数对 {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new HashSet<>());
            }
        }

        Set<Integer> equal = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    if (nums[i] == nums[j]) {
                        equal.add(nums[i]);
                    } else {
                        map.get(nums[i]).add(nums[j]);
                        map.get(nums[j]).add(nums[i]);
                    }
                }
            }
        }

        int ans = 0;
        for (Set<Integer> set : map.values()) {
            ans += set.size();
        }

        return ans / 2 + equal.size();
    }
}
