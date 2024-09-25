package com.bottomlord.week_262;

import java.util.*;

/**
 * @author chen yue
 * @date 2024-07-16 13:48:11
 */
public class LeetCode_3112_1_访问消失节点的最少时间 {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1], cost = edge[2];
            Map<Integer, Integer> xMap = graph.get(x), yMap = graph.get(y);
            if (xMap == null) {
                xMap = new HashMap<>();
                xMap.put(y, cost);
            } else {
                xMap.put(y, Math.min(xMap.getOrDefault(y, Integer.MAX_VALUE), cost));
            }
            graph.put(x, xMap);

            if (yMap == null) {
                yMap = new HashMap<>();
                yMap.put(x, cost);
            } else {
                yMap.put(x, Math.min(yMap.getOrDefault(x, Integer.MAX_VALUE), cost));
            }
            graph.put(y, yMap);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int i = arr[0], sum = arr[1];
                if (ans[i] == -1 && sum >= disappear[i]) {
                    continue;
                }

                if (ans[i] != -1 && sum > ans[i]) {
                    continue;
                }

                ans[i] = ans[i] == -1 ? sum : Math.min(ans[i], sum);

                Map<Integer, Integer> nextMap = graph.get(i);
                if (nextMap == null) {
                    continue;
                }

                for (Map.Entry<Integer, Integer> entry : nextMap.entrySet()) {
                    queue.offer(new int[]{entry.getKey(), sum + entry.getValue()});
                }
            }

            System.out.println(Arrays.toString(ans));
        }

        return ans;
    }
}
