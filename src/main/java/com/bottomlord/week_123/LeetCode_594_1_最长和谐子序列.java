package com.bottomlord.week_123;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-11-20 14:34:21
 */
public class LeetCode_594_1_最长和谐子序列 {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int num : nums) {
            mapping.put(num, mapping.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (Integer num : mapping.keySet()) {
            if (!mapping.containsKey(num + 1)) {
                continue;
            }

            ans = Math.max(mapping.get(num) + mapping.get(num + 1), ans);
        }

        return ans;
    }
}
