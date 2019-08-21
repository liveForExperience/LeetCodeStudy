package com.bottomlord.week_7;

import java.util.*;

public class LeetCode_594_2 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        while (++min <= max) {
            if (map.get(min) == null && map.get(min - 1) != null) {
                ans = Math.max(map.get(min) + map.get(min - 1), ans);
            }
        }

        return ans;
    }
}