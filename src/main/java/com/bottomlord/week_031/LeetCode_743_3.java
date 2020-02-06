package com.bottomlord.week_031;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/2/6 22:34
 */
public class LeetCode_743_3 {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            List<int[]> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new int[]{time[1], time[2]});
            graph.put(time[0], list);
        }

        Map<Integer, Integer> dict = new HashMap<>();
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.offer(new int[]{K, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (dict.containsKey(node[0])) {
                continue;
            }

            dict.put(node[0], node[1]);
            if (graph.containsKey(node[0])) {
                for (int[] edge : graph.get(node[0])) {
                    queue.offer(new int[]{edge[0], edge[1] + node[1]});
                }
            }
        }

        return dict.size() == N ? Collections.max(dict.values()) : -1;
    }
}