package com.bottomlord.week_103;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/6/28 9:09
 */
public class LeetCode_815_3 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        int n = routes.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        boolean[][] edges = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> nums = map.getOrDefault(site, new ArrayList<>());
                if (!nums.isEmpty()) {
                    for (int num : nums) {
                        edges[i][num] = edges[num][i] = true;
                    }
                }

                nums.add(i);
                map.put(site, nums);
            }
        }

        int[] distance = new int[n];
        Arrays.fill(distance, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i : map.getOrDefault(source, new ArrayList<>())) {
            distance[i] = 1;
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j = 0; j < n; j++) {
                if (edges[i][j] && distance[j] == -1) {
                    distance[j] = distance[i] + 1;
                    queue.offer(j);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i : map.getOrDefault(target, new ArrayList<>())) {
            if (distance[i] != -1) {
                ans = Math.min(ans, distance[i]);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
