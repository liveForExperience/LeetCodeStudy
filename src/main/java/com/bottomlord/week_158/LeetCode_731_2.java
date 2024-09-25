package com.bottomlord.week_158;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chen yue
 * @date 2022-07-20 20:07:34
 */
public class LeetCode_731_2 {
    class MyCalendarTwo {
        private Map<Integer, Integer> map;
        public MyCalendarTwo() {
            this.map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
            int num = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int index = entry.getKey();
                num += entry.getValue();
                if (index == start) {
                    if (num > 2) {
                        map.put(start, map.getOrDefault(start, 0) - 1);
                        map.put(end, map.getOrDefault(end, 0) + 1);
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
