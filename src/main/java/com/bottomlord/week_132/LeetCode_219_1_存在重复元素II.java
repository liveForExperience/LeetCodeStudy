package com.bottomlord.week_132;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-19 08:53:42
 */
public class LeetCode_219_1_存在重复元素II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key).size() < 2) {
                continue;
            }

            List<Integer> indexes = map.get(key);
            for (int i = 0; i < indexes.size() - 1; i++) {
                if (indexes.get(i + 1) - indexes.get(i) <= k) {
                    return true;
                }
            }
        }

        return false;
    }
}
