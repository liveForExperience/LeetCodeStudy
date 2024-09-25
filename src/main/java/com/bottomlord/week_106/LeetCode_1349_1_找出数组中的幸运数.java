package com.bottomlord.week_106;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_1349_1_找出数组中的幸运数 {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        for (int num : map.keySet()) {
            if (num == map.get(num)) {
                max = Math.max(max, num);
            }
        }

        return max == Integer.MIN_VALUE ? -1 : max;
    }
}
