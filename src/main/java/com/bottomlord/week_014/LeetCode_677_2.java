package com.bottomlord.week_014;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_677_2 {
    class MapSum {
        Map<String, Integer> map;
        Map<String, Set<String>> dict;
        public MapSum() {
            map = new HashMap<>();
            dict = new HashMap<>();
        }

        public void insert(String key, int val) {
            StringBuilder sb = new StringBuilder();
            char[] cs = key.toCharArray();
            for (char c : cs) {
                String str = sb.append(c).toString();
                if (dict.containsKey(str)) {
                    dict.get(str).add(key);
                } else {
                    Set<String> set = new HashSet<>();
                    set.add(key);
                    dict.put(str, set);
                }
            }

            map.put(key, val);
        }

        public int sum(String prefix) {
            if (dict.containsKey(prefix)) {
                int sum = 0;
                for (String key : dict.get(prefix)) {
                    sum += map.get(key);
                }
                return sum;
            } else {
                return 0;
            }
        }
    }
}
