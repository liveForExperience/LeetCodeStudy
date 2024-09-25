package com.bottomlord.week_029;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/21 15:31
 */
public class LeetCode_692_2 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>((x, y) -> map.get(x).equals(map.get(y)) ? x.compareTo(y) : map.get(y) - map.get(x));
        for (String key : map.keySet()) {
            queue.offer(key);
        }

        List<String> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            ans.add(queue.poll());
        }
        return ans;
    }
}