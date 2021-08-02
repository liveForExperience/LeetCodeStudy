package com.bottomlord.week_108;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/8/2 8:19
 */
public class LeetCode_743_1_网络延迟时间 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] time : times) {
            edges.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        Map<Integer, Integer> memo = new HashMap<>();
        queue.offer(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            if (memo.containsKey(node[0])) {
                continue;
            }

            memo.put(node[0], node[1]);

            if (edges.containsKey(node[0])) {
                for (int[] edge : edges.get(node[0])) {
                    queue.offer(new int[]{edge[0], edge[1] + node[1]});
                }
            }
        }

        return memo.size() == n ? Collections.max(memo.values()) : -1;
    }
}
