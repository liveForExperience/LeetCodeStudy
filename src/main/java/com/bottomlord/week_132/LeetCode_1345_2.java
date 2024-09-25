package com.bottomlord.week_132;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-01-22 14:35:15
 */
public class LeetCode_1345_2 {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        Set<Integer> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] a = queue.poll();
            int index = a[0], step = a[1];

            if (index == arr.length - 1) {
                return step;
            }

            int val = arr[index];
            step++;

            List<Integer> list = map.getOrDefault(val, new ArrayList<>());
            map.remove(val);
            if (index != 0) {
                list.add(index - 1);
            }

            if (index != arr.length - 1) {
                list.add(index + 1);
            }

            for (Integer i : list) {
                if (memo.contains(i)) {
                    continue;
                }

                memo.add(i);
                queue.offer(new int[]{i, step});
            }
        }

        return -1;
    }
}
