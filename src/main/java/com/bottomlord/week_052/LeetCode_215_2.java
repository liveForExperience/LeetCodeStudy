package com.bottomlord.week_052;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/6/29 8:47
 */
public class LeetCode_215_2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int num : nums) {
            queue.offer(num);
        }

        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }

        return queue.peek();
    }
}
