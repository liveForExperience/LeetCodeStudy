package com.bottomlord.week_029;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/21 14:49
 */
public class LeetCode_692_1_前K个高频单词 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> candidates = new ArrayList<>(map.keySet());
        candidates.sort((x, y) -> map.get(x).equals(map.get(y)) ? x.compareTo(y) : map.get(y) - map.get(x));
        return candidates.subList(0, k);
    }
}
