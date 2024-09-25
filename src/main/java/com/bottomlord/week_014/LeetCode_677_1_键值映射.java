package com.bottomlord.week_014;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_677_1_键值映射 {
    class MapSum {
        Map<String, Integer> map;

        public MapSum() {
            map = new HashMap<>();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int sum = 0;
            for (String key : map.keySet()) {
                if (key.startsWith(prefix)) {
                    sum += map.get(key);
                }
            }
            return sum;
        }
    }
}
