package com.bottomlord.week_125;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2021-11-29 20:28:50
 */
public class LeetCode_786_2 {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingDouble(x -> arr[x[0]] * 1.0 / arr[x[1]]));
        for (int i = 1; i < len; i++) {
            queue.offer(new int[]{0, i});
        }

        while (k-- > 1) {
            int[] a = queue.poll();
            if (a == null) {
                continue;
            }

            if (a[0] + 1 < a[1]) {
                queue.offer(new int[]{a[0] + 1, a[1]});
            }
        }

        return queue.poll();
    }
}
