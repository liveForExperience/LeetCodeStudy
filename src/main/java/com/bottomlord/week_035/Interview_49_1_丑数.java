package com.bottomlord.week_035;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/3/2 12:36
 */
public class Interview_49_1_丑数 {
    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        queue.offer(1L);
        set.add(1L);
        int size = 0;
        int[] arr = new int[]{2,3,5};

        while (!queue.isEmpty()) {
            long top = queue.poll();
            if (++size == n) {
                return (int)top;
            }
            for (int num : arr) {
                long multi = top * num;
                if (set.contains(multi)) {
                    continue;
                }

                queue.offer(multi);
                set.add(multi);
            }
        }

        return -1;
    }
}
