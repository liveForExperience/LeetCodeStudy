package com.bottomlord.contest_20191006;

import java.util.HashMap;
import java.util.Map;

public class Contest_2_2 {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        for (int num : arr) {
            int pre = num - difference, cur = 1 + map.getOrDefault(pre, 0);

            if (!map.containsKey(num)) {
                map.put(num, cur);
            } else {
                map.put(num, Math.max(cur, map.getOrDefault(num, 0)));
            }

            ans = Math.max(cur, ans);
        }

        return ans;
    }
}