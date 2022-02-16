package com.bottomlord.week_136;

import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2022-02-16 13:05:07
 */
public class LeetCode_offerII59_1_数据流的第K大数值 {
    class KthLargest {
        private PriorityQueue<Integer> queue;
        private int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.queue = new PriorityQueue<>();
            for (int num : nums) {
                queue.offer(num);
            }
        }

        public int add(int val) {
            queue.offer(val);
            while (queue.size() > k) {
                queue.poll();
            }

            return queue.peek();
        }
    }
}
