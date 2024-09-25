package com.bottomlord.week_103;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/6/28 8:34
 */
public class LeetCode_815_1_公交路线 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Set<Integer>  memo = new HashSet<>();
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
        }

        memo.add(source);
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

                     if (memo.contains(num)) {
                         continue;
                     }

                     memo.add(num);
                     List<int[]> nextRoute = map.get(num);
                     if (nextRoute == null) {
                         continue;
                     }

                     for (int[] nextBus : nextRoute) {
                         queue.offer(nextBus);
                     }
                 }
             }
        }

        return -1;
    }
}
