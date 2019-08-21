package com.bottomlord.week_7;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_594_4 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Integer num : map.keySet()) {
            if (map.containsKey(num - 1)) {
                ans = Math.max(ans, map.get(num) + map.get(num - 1));
            }
        }

        return ans;
    }
}
