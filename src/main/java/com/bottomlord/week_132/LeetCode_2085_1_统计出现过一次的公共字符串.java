package com.bottomlord.week_132;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-01-17 21:05:34
 */
public class LeetCode_2085_1_统计出现过一次的公共字符串 {
    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words1) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        map.keySet().removeIf(key -> map.get(key) != 1);

        for (String word : words2) {
            map.put(word, map.getOrDefault(word, 0) - 1);
        }

        int count = 0;
        for (String word : map.keySet()) {
            if (map.get(word) == 0) {
                count++;
            }
        }

        return count;
    }
}
