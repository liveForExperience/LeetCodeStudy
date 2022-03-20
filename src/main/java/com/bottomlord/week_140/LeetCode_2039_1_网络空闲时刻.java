package com.bottomlord.week_140;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-03-20 11:22:20
 */
public class LeetCode_2039_1_网络空闲时刻 {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }

        Map<Integer, Integer> disMap = new HashMap<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                if (!disMap.containsKey(arr[0])) {
                    disMap.put(arr[0], arr[1]);
                }

                if (!map.containsKey(arr[0])) {
                    continue;
                }

                for (Integer next : map.get(arr[0])) {
                    if (disMap.containsKey(next)) {
                        continue;
                    }

                    queue.offer(new int[]{next, arr[1] + 1});
                }
            }
        }

        disMap.remove(0);

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : disMap.entrySet()) {
            int node = entry.getKey(), dis = entry.getValue(), pat = patience[node];
            int time = ((dis * 2 - 1) / pat) * pat, last = time + dis * 2 + 1;
            max = Math.max(max, last);
        }

        return max;
    }
}
