package com.bottomlord.week_140;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-03-20 14:26:59
 */
public class LeetCode_2039_2 {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int max = 0;
        Set<Integer> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int count = queue.size();

            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int node = arr[0], dis = arr[1], pat = patience[node];
                if (memo.contains(node)) {
                    continue;
                }

                if (dis != 0) {
                    max = Math.max(max, pat * ((2 * dis - 1) / pat) + 2 * dis + 1);
                }

                memo.add(node);
                for (Integer nextNode : graph.get(node)) {
                    queue.offer(new int[]{nextNode, dis + 1});
                }
            }
        }

        return max;
    }
}
