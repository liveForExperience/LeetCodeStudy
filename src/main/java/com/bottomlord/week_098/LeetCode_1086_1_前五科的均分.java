package com.bottomlord.week_098;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/5/27 8:30
 */
public class LeetCode_1086_1_前五科的均分 {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] item : items) {
            int id = item[0], score = item[1];
            PriorityQueue<Integer> queue = map.getOrDefault(id, new PriorityQueue<>(Comparator.reverseOrder()));
            queue.offer(score);
            map.put(id, queue);
        }

        int[][] ans = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int id = entry.getKey(), count = 5, sum = 0;
            PriorityQueue<Integer> queue = entry.getValue();
            while (count > 0 && !queue.isEmpty()) {
                sum += queue.poll();
                count--;
            }

            ans[index][0] = id;
            ans[index][1] = sum / 5;
            index++;
        }

        return ans;
    }
}
