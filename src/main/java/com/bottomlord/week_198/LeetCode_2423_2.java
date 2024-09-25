package com.bottomlord.week_198;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-04-29 14:07:55
 */
public class LeetCode_2423_2 {
    public boolean equalFrequency(String word) {
        int[] bucket = new int[26];
        for (char c : word.toCharArray()) {
            bucket[c - 'a']++;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : bucket) {
            if (num != 0) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        for (int num : bucket) {
            if (num == 0) {
                continue;
            }

            map.put(num, map.get(num) - 1);
            if (map.get(num) == 0) {
                map.remove(num);
            }

            if (num - 1 > 0) {
                map.put(num - 1, map.getOrDefault(num - 1, 0) + 1);
            }

            if (map.size() == 1) {
                return true;
            }

            if (num - 1 > 0) {
                map.put(num - 1, map.get(num - 1) - 1);
                if (map.get(num - 1) == 0) {
                    map.remove(num - 1);
                }
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        return false;
    }
}
