package com.bottomlord.week_104;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LeetCode_981_1_基于时间的键值存储 {
    class TimeMap {
        private Map<String, TreeMap<Integer, String>> map;
        public TimeMap() {
            this.map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> treeMap = map.getOrDefault(key, new TreeMap<>(Comparator.reverseOrder()));
            treeMap.put(timestamp, value);
            map.put(key, treeMap);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            if (treeMap == null) {
                return "";
            }

            int floor = treeMap.lastKey();
            if (timestamp < floor) {
                return "";
            }

            return treeMap.ceilingEntry(timestamp).getValue();
        }
    }
}
