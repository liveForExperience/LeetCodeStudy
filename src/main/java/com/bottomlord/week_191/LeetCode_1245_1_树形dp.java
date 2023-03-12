package com.bottomlord.week_191;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-03-12 13:57:25
 */
public class LeetCode_1245_1_树形dp {
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }

        return bfs(bfs(0, map, n)[0], map, n)[1];
    }

    private int[] bfs(int x, Map<Integer, List<Integer>> map, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(x);
        int distance = 0;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Integer v = queue.poll();
                if (v == null || memo[v] != -1) {
                    continue;
                }

                memo[v] = distance;

                for (Integer next : map.getOrDefault(v, new ArrayList<>())) {
                    queue.offer(next);
                }
            }
            distance++;
        }

        int max = 0, index = x;
        for (int i = 0; i < memo.length; i++) {
            if (memo[i] > max) {
                max = memo[i];
                index = i;
            }
        }

        return new int[]{index, max};
    }
}
