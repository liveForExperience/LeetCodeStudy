package com.bottomlord.week_238;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2024-01-30 20:29:05
 */
public class LeetCode_2808_1_使循环数组所有元素相等的最少秒数 {
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            map.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (List<Integer> indexes : map.values()) {
            int seconds = 0;

            indexes.add(indexes.get(0) + nums.size());

            for (int i = 1; i < indexes.size(); i++) {
                seconds = Math.max(seconds, (indexes.get(i) - indexes.get(i - 1)) / 2);
            }

            min = Math.min(min, seconds);
        }

        return min;
    }
}
