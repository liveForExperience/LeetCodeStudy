package com.bottomlord.week_031;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ThinkPad
 * @date 2020/2/6 21:43
 */
public class LeetCode_743_2 {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            List<int[]> list = graph.getOrDefault(time[0], new ArrayList<>());
            list.add(new int[]{time[1], time[2]});
            graph.put(time[0], list);
        }

        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dict.put(i, Integer.MAX_VALUE);
        }
        dict.put(K, 0);
        boolean[] visited = new boolean[N + 1];

        while (true) {
            int dist = Integer.MAX_VALUE;
            int node = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && dict.get(i) < dist) {
                    dist = dict.get(i);
                    node = i;
                }
            }

            if (node == 0) {
                break;
            }

            visited[node] = true;
            if (graph.containsKey(node)) {
                for (int[] arr : graph.get(node)) {
                    dict.put(arr[1], Math.min(dict.get(arr[1]), arr[2] + dist));
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (dict.get(i) == Integer.MAX_VALUE) {
                return -1;
            } else {
                max = Math.max(max, dict.get(i));
            }
        }
        return max;
    }
}