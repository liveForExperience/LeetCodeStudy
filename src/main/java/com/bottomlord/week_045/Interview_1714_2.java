package com.bottomlord.week_045;

import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/5/11 8:22
 */
public class Interview_1714_2 {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : arr) {
            queue.offer(num);
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            if (queue.isEmpty()) {
                return ans;
            }

            ans[i] = queue.poll();
        }

        return ans;
    }
}
