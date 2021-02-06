package com.bottomlord.week_082;

import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/2/4 8:39
 */
public class LeetCode_440_1_字典序的第k小数字 {
    public int findKthNumber(int n, int k) {
        PriorityQueue<String> queue = new PriorityQueue<>(k);
        for (int i = 1; i <= n; i++) {
            queue.offer("" + i);
        }

        for (int i = 0; i < k; i++) {
            if (i == k - 1) {
                return Integer.parseInt(queue.poll());
            }
            queue.poll();
        }

        throw new RuntimeException();
    }
}
