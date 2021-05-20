package com.bottomlord.week_097;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/5/20 8:24
 */
public class LeetCode_692_1_前K个高频单词 {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Queue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o2.getValue() - o1.getValue();
            }

            return o1.getKey().compareTo(o2.getKey());
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
        }

        int index = 0;
        List<String> ans = new ArrayList<>();
        while (index != k && !queue.isEmpty()) {
            ans.add(queue.poll().getKey());
            index++;
        }

        return ans;
    }
}
