package com.bottomlord.week_072;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/11/25 11:26
 */
public class LeetCode_358_1_K距离间隔重排字符串 {
    public String rearrangeString(String s, int k) {
        if (k == 0) {
            return s;
        }

        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'a']++;
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((x, y) -> y[1] - x[1]);

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] != 0) {
                maxHeap.offer(new int[]{i, bucket[i]});
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            int[] node = maxHeap.poll();
            sb.append((char)(node[0] + 'a'));
            node[1]--;
            queue.offer(node);

            if (queue.size() == k) {
                int[] lastAlphabet = queue.poll();

                if (lastAlphabet[1] != 0) {
                    maxHeap.offer(lastAlphabet);
                }
            }
        }

        return sb.length() == s.length() ? sb.toString() : "";
    }
}
