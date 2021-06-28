package com.bottomlord.week_103;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/6/28 8:55
 */
public class LeetCode_815_2 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Set<int[]> memo = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] route : routes) {
            for (int num : route) {
                List<int[]> list = map.getOrDefault(num, new ArrayList<>());
                list.add(route);
                map.put(num, list);
            }
        }

        List<int[]> list = map.get(source);
        if (list == null) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] ints : list) {
            queue.offer(ints);
            memo.add(ints);
        }

        int bus = 0;

        while (!queue.isEmpty()) {
            bus++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                for (int num : arr) {
                    if (Objects.equals(num, target)) {
                        return bus;
                    }

                    List<int[]> nextRoute = map.get(num);
                    if (nextRoute == null) {
                        continue;
                    }

                    for (int[] nextBus : nextRoute) {
                        if (memo.contains(nextBus)) {
                            continue;
                        }

                        queue.offer(nextBus);
                    }
                }
            }
        }

        return -1;
    }
}
