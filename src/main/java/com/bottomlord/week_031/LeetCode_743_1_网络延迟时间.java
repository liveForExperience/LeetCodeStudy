package com.bottomlord.week_031;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/5 21:01
 */
public class LeetCode_743_1_网络延迟时间 {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int [] time : times) {
            List<int[]> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new int[]{time[1], time[2]});
            graph.put(time[0], list);
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dfs(graph, dist, K, 0);

        int max = 0;
        for (Integer time : dist.values()) {
            if (time == null) {
                return -1;
            } else {
                max = Math.max(max, time);
            }
        }
        return max;
    }

    private void dfs(Map<Integer, List<int[]>> graph, Map<Integer, Integer> dist, int node, int time) {
        if (time >= dist.get(node)) {
            return;
        }

        dist.put(node, time);

        if (graph.containsKey(node)) {
            for (int[] arr : graph.get(node)) {
                dfs(graph, dist, arr[0], time + arr[1]);
            }
        }
    }
}
