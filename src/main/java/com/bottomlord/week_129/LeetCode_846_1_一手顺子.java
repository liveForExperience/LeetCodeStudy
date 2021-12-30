package com.bottomlord.week_129;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-12-30 08:49:00
 */
public class LeetCode_846_1_一手顺子 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n == 0) {
            return true;
        }

        if (n % groupSize != 0) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (Integer num : map.keySet()) {
            queue.offer(new int[]{num, map.get(num)});
        }

        while (!queue.isEmpty()) {
            int count = groupSize;
            Integer pre = null;
            List<int[]> toAdd = new ArrayList<>();
            while (count-- > 0) {
                if (queue.isEmpty()) {
                    return false;
                }

                int[] arr = queue.poll();
                if (pre != null && arr[0] - 1 != pre) {
                    return false;
                }

                pre = arr[0];
                arr[1]--;

                if (arr[1] != 0) {
                    toAdd.add(arr);
                }
            }

            for (int[] arr : toAdd) {
                queue.offer(arr);
            }
        }

        return true;
    }
}
