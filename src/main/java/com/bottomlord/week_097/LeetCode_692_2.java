package com.bottomlord.week_097;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/5/20 8:46
 */
public class LeetCode_692_2 {
    public List<String> topKFrequent(String[] words, int k) {
        int len = words.length;
        String[] keys = new String[len];
        int[] values = new int[len];

        for (String word : words) {
            int hash = (word.hashCode() & 0x7FFFFFFF) % len;
            while (!Objects.equals(keys[hash], word) && values[hash] > 0) {
                hash = (hash + 1) % len;
            }

            keys[hash] = word;
            values[hash]++;
        }

        PriorityQueue<String>[] counts = new PriorityQueue[len + 1];
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if (value > 0) {
                if (counts[value] == null) {
                    counts[value] = new PriorityQueue<>();
                }
                counts[value].add(keys[i]);
            }
        }

        int count = 0;
        List<String> ans = new ArrayList<>();
        for (int i = counts.length - 1; i >= 0; i--) {
            if (counts[i] == null) {
                continue;
            }

            PriorityQueue<String> queue = counts[i];
            while (count != k && !queue.isEmpty()) {
                ans.add(queue.poll());
                count++;
            }
        }

        return ans;
    }

}
