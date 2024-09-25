package com.bottomlord.week_073;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/11/30 8:24
 */
public class LeetCode_767_1_重构字符串 {
    public String reorganizeString(String S) {
        if (S.length() <= 1) {
            return S;
        }

        int[] bucket = new int[26];
        for (char c : S.toCharArray()) {
            bucket[c - 'a']++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                queue.offer(new int[]{i, bucket[i]});
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int[] first = queue.poll();

            if (queue.isEmpty()) {
                if (first[1] != 1) {
                    return "";
                } else {
                    sb.append((char)(first[0] + 'a'));
                    return sb.toString();
                }
            }

            int[] second = queue.poll();
            if (first[1] != second[1]) {
                first[1] -= second[1];
                queue.offer(first);
            }

            for (int i = 0; i < second[1]; i++) {
                sb.append((char)(first[0] + 'a'));
                sb.append((char)(second[0] + 'a'));
            }
        }

        return sb.toString();
    }
}
